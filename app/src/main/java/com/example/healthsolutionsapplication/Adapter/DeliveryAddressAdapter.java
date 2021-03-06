package com.example.healthsolutionsapplication.Adapter;

import static com.example.healthsolutionsapplication.R.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Address;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    final int EMPTY_VIEW = 77777;
    private static List<Address> mList;
    private static Context context;

    public DeliveryAddressAdapter(List<Address> mList, Context context){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if(viewType == EMPTY_VIEW){
            return new EmptyView(layoutInflater.inflate
                                (layout.layout_nothing_address, parent, false));
        }else{
            return new ViewHolder(layoutInflater.inflate
                    (layout.items_delivery_address, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (getItemViewType(position) == EMPTY_VIEW) {
            EmptyView emptyView = (EmptyView) holder;
            emptyView.tvPrimaryNothing.setText("?????a ch??? tr???ng");
            emptyView.tvSecondaryNothing.setText("Nh???n n??t ph??a d?????i ????? th??m ?????a ch??? m???i");
            emptyView.tvPrimaryNothing.setCompoundDrawablesWithIntrinsicBounds(null, new
                            IconicsDrawable(context.getApplicationContext()).icon(FontAwesome.Icon.faw_ticket).sizeDp(48).color(Color.DKGRAY),
                    null, null);
        } else {
            Address address = mList.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            // Bind data to itemView
            viewHolder.tvFullNameReceiver.setText(address.getNameReceiver());
            viewHolder.tvPhoneNumberReceiver.setText(address.getPhoneReceiver());
            viewHolder.tvAddressReceiver.setText(address.getContentAddress());
            viewHolder.tvDefaultAddress.setText(address.isDefault() == true ? "?????a ch??? m???t ?????nh" : "");
        }
    }


    @Override
    public int getItemCount() {
        return mList.size() > 0 ? mList.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() == 0) {
            return EMPTY_VIEW;
        }else{
            return super.getItemViewType(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return new Address().getIdAddress();
    }

    public static class EmptyView extends RecyclerView.ViewHolder {
        TextView tvPrimaryNothing, tvSecondaryNothing;
        public EmptyView(@NonNull View itemView) {
            super(itemView);
            tvPrimaryNothing = itemView.findViewById(id.nothingPrimary);
            tvSecondaryNothing = itemView.findViewById(id.nothingSecondary);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEditProfile;
        TextView tvFullNameReceiver, tvPhoneNumberReceiver, tvAddressReceiver, tvDefaultAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEditProfile = itemView.findViewById(id.img_editProfile);
            tvFullNameReceiver = itemView.findViewById(id.tv_fullNameReceiver);
            tvPhoneNumberReceiver = itemView.findViewById(id.tv_phoneNumberReceiver);
            tvAddressReceiver = itemView.findViewById(id.tv_addressReceiver);
            tvDefaultAddress = itemView.findViewById(id.tv_defaultAddress);

            imgEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(imgEditProfile.getContext(), itemView);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case id.action_selectAddress:

                                    return true;

                                case id.action_updateAddress:
                                    return true;

                                case id.action_deleteAddress:
                                    performDeleteAddress();
                                    return true;

                                default:
                                    return false;
                            }

                        }
                    });
                    popup.inflate(menu.menu_item_recycler_view);
                    popup.setGravity(Gravity.RIGHT);

                    try {
                        Field[] fields = popup.getClass().getDeclaredFields();
                        for (Field field : fields) {
                            if ("mPopup".equals(field.getName())) {
                                field.setAccessible(true);
                                Object menuPopupHelper = field.get(popup);
                                Class<?> classPopupHelper = Class.forName(menuPopupHelper
                                        .getClass().getName());
                                Method setForceIcons = classPopupHelper.getMethod(
                                        "setForceShowIcon", boolean.class);
                                setForceIcons.invoke(menuPopupHelper, true);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    popup.show();

                }
            });

        }
    }

    static void performDeleteAddress(){
        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        int idCustomer = sharedPref.getInt("getId", new Customer().getId());
        int idAddress = mList.get(sharedPref.getInt("getIdAddress", new Address().getIdAddress())).getIdAddress();
        //Toast.makeText(context, "Id :" + idCustomer + " - Id Address:" +idAddress, Toast.LENGTH_SHORT).show();
        ToastGenerate toastGenerate = new ToastGenerate(context);

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .deleteAddress(idCustomer, idAddress);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if (response.body().getResult() == Constants.RESULT_1){
                            toastGenerate.createToastMessage("X??a th??nh c??ng", 1);

                        }else{
                            toastGenerate.createToastMessage("X??a th???t b???i", 2);
                        }

                    }else{
                        toastGenerate.createToastMessage("L???i x??a", 2);
                    }

                }else{
                    toastGenerate.createToastMessage("System Error", 0);
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.ERR, t.getMessage());
            }
        });
    }



}
