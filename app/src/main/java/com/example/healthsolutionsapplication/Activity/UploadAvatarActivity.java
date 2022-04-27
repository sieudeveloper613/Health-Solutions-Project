package com.example.healthsolutionsapplication.Activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Fragment.PersonFragment;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.Model.FileModel;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.FileUtils;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.gms.common.api.Api;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadAvatarActivity extends AppCompatActivity{
    // View and ViewGroup
    ImageView imgShowAvatar;
    MaterialButton btnGetAvatar, btnSaveAvatar;
    int idCustomer;
    Customer customer;
    SharedPreferences sharedPref;
    //Object and Reference
    ToastGenerate toastGenerate;
    int Request_Code_Image = 123;
    String realPath = "";
    private CharSequence[] options= {"Camera","Gallery","Cancel"};
    private String selectedImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        changeStatusBarColor();
        customToolBar("Ảnh đại diện");

        // Define Reference
        toastGenerate = new ToastGenerate(UploadAvatarActivity.this);
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());
        // Define id for view
        initView();
        pick_img();
        upload_img();
        // Define method
        requirePermission();
    }

    private void initView() {
        imgShowAvatar= findViewById(R.id.img_showAvatar);
        btnGetAvatar = findViewById(R.id.btn_getAvatar);
        btnSaveAvatar = findViewById(R.id.btn_saveAvatar);
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_rollCall);
        materialToolbar.setTitle(titleString);
        materialToolbar.setTitleCentered(true);
        materialToolbar.setTitleTextColor(Color.WHITE);
        materialToolbar.setBackgroundColor(Color.parseColor("#0088FF"));
        materialToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
  void upload_img(){
        btnSaveAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFileToServer();
                startActivity(new Intent(UploadAvatarActivity.this, PersonFragment.class));

            }
        });
  }
  void pick_img(){
        btnGetAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UploadAvatarActivity.this);
                builder.setTitle("Select Image");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(options[which].equals("Camera")){
                            Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePic, 0);
                        }
                        else if(options[which].equals("Gallery")) {
                            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(gallery, 1);
                        }
                        else {
                            dialog.dismiss();
                        }
                    }
                });

                builder.show();
            }
        });
  }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode !=RESULT_CANCELED){

            switch (requestCode){
                case 0:
                    if(resultCode == RESULT_OK && data !=null){
                        Bitmap image = (Bitmap) data.getExtras().get("data");
                        selectedImage = FileUtils.getPath(this,getImageUri(this,image));
                        imgShowAvatar.setImageBitmap(image);
                    }
                    break;
                case 1:
                    if(resultCode == RESULT_OK && data !=null){

                        Uri image = data.getData();
                        selectedImage = FileUtils.getPath(this,image);
                        Picasso.get().load(image).into(imgShowAvatar);
                    }
            }

        }
    }

    public Uri getImageUri(Context context, Bitmap bitmap){
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "myImage","");

        return Uri.parse(path);
    }


    public void requirePermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }


    public void uploadFileToServer(){

        File file = new File(Uri.parse(selectedImage).getPath());

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("sendimage",file.getName(),requestBody);

        APIConnect service = RetrofitClient.getClient().create(APIConnect.class);

        Call<FileModel> call = service.UploadPhoto(filePart,idCustomer);
        call.enqueue(new Callback<FileModel>() {
            @Override
            public void onResponse(Call<FileModel> call, Response<FileModel> response) {
                FileModel fileModel = response.body();

                Log.d("djdsahds", fileModel.getMessage());
            }

            @Override
            public void onFailure(Call<FileModel> call, Throwable t) {

                Log.d("dknlsldkasknsknaskndfknfdkmn",t.getMessage() );
            }
        });


    }
}




