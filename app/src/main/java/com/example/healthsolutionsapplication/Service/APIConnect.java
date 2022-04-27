package com.example.healthsolutionsapplication.Service;

import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.Model.FileModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIConnect {
    @Multipart
    @POST("HealthSolutionsDB/upload_api.php")
    Call<FileModel> UploadPhoto(@Part MultipartBody.Part avatarCustomer,@Field("idCustomer") int id);
    @GET("HealthSolutionsDB/get_img.php")
    Call<ServerResponse> getimg_CS(@Query("idCustomer") int id);
    // CUSTOMER ------------------------------------------------------------------------------------

    @GET("HealthSolutionsDB/Register.php")
    Call<ServerResponse> performRegister(@Query("nameCustomer") String name,
                                         @Query("accountCustomer") String account,
                                         @Query("passwordCustomer") String password,
                                         @Query("phoneCustomer") String phone);

    @GET("HealthSolutionsDB/Login.php")
    Call<ServerResponse> performLogin(@Query("accountCustomer") String account,
                                      @Query("passwordCustomer") String password);

    @GET("HealthSolutionsDB/UpdateName.php")
    Call<ServerResponse> performName(@Query("idCustomer") int id,
                                     @Query("nameCustomer") String name);

    @GET("HealthSolutionsDB/updateEmail.php")
    Call<ServerResponse> performEmail(@Query("idCustomer") int id,
                                      @Query("emailCustomer") String email);

    @GET("HealthSolutionsDB/updatePhone.php")
    Call<ServerResponse> performPhone(@Query("idCustomer") int id,
                                      @Query("phoneCustomer") String phone);

    @GET("HealthSolutionsDB/updateGender.php")
    Call<ServerResponse> performGender(@Query("idCustomer") int id,
                                       @Query("genderCustomer") int gender);

    @GET("HealthSolutionsDB/getIdCustomer.php")
    Call<ServerResponse> getIdCustomer(@Query("idCustomer") int id);

    @GET("HealthSolutionsDB/updatePassword.php")
    Call<ServerResponse> performPassword(@Query("idCustomer") int id,
                                         @Query("passwordCustomer") String password);

    @GET("HealthSolutionsDB/updateDob.php")
    Call<ServerResponse> performDob(@Query("idCustomer") int id,
                                    @Query("dobCustomer") String dob);

    @GET("HealthSolutionsDB/updateMainAddress.php")
    Call<ServerResponse> updateMainAddress(@Query("idCustomer") int id,
                                           @Query("mainAddress") String address);

    @GET("HealthSolutionsDB/UploadPicture.php")
    Call<ServerResponse> uploadPicture(@Query("idCustomer") int id,
                                       @Query("avatarCustomer") String avatar);

    @GET("HealthSolutionsDB/getEmail.php")
    Call<ServerResponse> getValidateEmail(@Query("emailCustomer") String email);



    // ADDRESS -------------------------------------------------------------------------------------

    @GET("HealthSolutionsDB/insertAddress.php")
    Call<ServerResponse> insertAddress(@Query("idCustomer") int id,
                                        @Query("nameReceiver") String name,
                                        @Query("phoneReceiver") String phone,
                                        @Query("contentAddress") String address,
                                        @Query("isDefault") boolean isDefault);

    @GET("HealthSolutionsDB/deleteAddress.php")
    Call<ServerResponse> deleteAddress(@Query("idCustomer") int id,
                                       @Query("idAddress") int idAddress);

    @GET("HealthSolutionsDB/getListAddress.php")
    Call<ServerResponse> performGetAddressList(@Query("idCustomer") int id);



    // PRODUCT -------------------------------------------------------------------------------------

    @GET("HealthSolutionsDB/ProductListByDESC.php")
    Call<ServerResponse> getNewProduct();

    @GET("HealthSolutionsDB/ProductListByASC.php")
    Call<ServerResponse> getSuggestProduct();

    @GET("HealthSolutionsDB/getIdProduct.php")
    Call<ServerResponse> performGetIdProduct(@Query("idProduct") int idProduct);





    // CATEGORY ------------------------------------------------------------------------------------

    @GET("HealthSolutionsDB/selectTypeByCategory.php")
    Call<ServerResponse> selectTypeByCategory(@Query("idCategory") int id);

    @GET("HealthSolutionsDB/selectProductByCategory.php")
    Call<ServerResponse> getProductByCategory(@Query("idCategory") int id);



    // TYPES ---------------------------------------------------------------------------------------

    @GET("HealthSolutionsDB/selectAllType.php")
    Call<ServerResponse> selectAllType();




    // FEEDBACK ------------------------------------------------------------------------------------

    @GET("HealthSolutionsDB/insertFeedback.php")
    Call<ServerResponse> insertFeedback(@Query("idCustomer") int id,
                                        @Query("nameCustomer") String name,
                                        @Query("titleFeedback") String title,
                                        @Query("contentFeedback") String content);


    // RESPONSE ------------------------------------------------------------------------------------



    // QUOTES --------------------------------------------------------------------------------------

}
