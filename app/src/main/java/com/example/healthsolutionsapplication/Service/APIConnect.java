package com.example.healthsolutionsapplication.Service;

import com.example.healthsolutionsapplication.Model.Customer;

import java.util.List;

import okhttp3.MultipartBody;
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
    @POST("uploadhinhanh.php")
    Call<String> UploadPhoto(@Part MultipartBody.Part photo);

    @GET("HealthSolutionsDB/Register.php")
    Call<ServerResponse> performRegister(@Query("nameCustomer") String name, @Query("accountCustomer") String account,
                                         @Query("passwordCustomer") String password, @Query("phoneCustomer") String phone);


    @GET("HealthSolutionsDB/Login.php")
    Call<ServerResponse> performLogin(@Query("accountCustomer") String account, @Query("passwordCustomer") String password);

    @GET("HealthSolutionsDB/UpdateName.php")
    Call<ServerResponse> performName(@Query("idCustomer") int id, @Query("nameCustomer") String name);

    @GET("HealthSolutionsDB/updateEmail.php")
    Call<ServerResponse> performEmail(@Query("idCustomer") int id, @Query("emailCustomer") String email);

    @GET("HealthSolutionsDB/updatePhone.php")
    Call<ServerResponse> performPhone(@Query("idCustomer") int id, @Query("phoneCustomer") String phone);

    @GET("HealthSolutionsDB/updateGender.php")
    Call<ServerResponse> performGender(@Query("idCustomer") int id, @Query("genderCustomer") int gender);

    @GET("HealthSolutionsDB/getIdCustomer.php")
    Call<ServerResponse> getIdCustomer(@Query("idCustomer") int id);

    @GET("HealthSolutionsDB/updatePassword.php")
    Call<ServerResponse> performPassword(@Query("idCustomer") int id, @Query("passwordCustomer") String password);

    @GET("HealthSolutionsDB/updateDob.php")
    Call<ServerResponse> performDob(@Query("idCustomer") int id, @Query("dobCustomer") String dob);

    @GET("HealthSolutionsDB/insertAddress.php")
    Call<ServerResponse> performAddress(@Query("idCustomer") int id, @Query("contentAddress") String address,
                                        @Query("isDefault") boolean isDefault);

    @GET("HealthSolutionsDB/updateAddress.php")
    Call<ServerResponse> updateAddress(@Query("idCustomer") int id, @Query("contentAddress") String address,
                                        @Query("isDefault") boolean isDefault);

    @GET("HealthSolutionsDB/UploadPicture.php")
    Call<ServerResponse> uploadPicture(@Query("idCustomer") int id, @Query("avatarCustomer") String avatar);

    @GET("HealthSolutionsDB/UploadPicture.php")
    Call<ServerResponse> forgotPassword(@Query("emailCustomer") String email, @Query("passwordCustomer") String password);

    @GET("HealthSolutionsDB/getEmail.php")
    Call<ServerResponse> getValidateEmail(@Query("emailCustomer") String email);

    @GET("HealthSolutionsDB/deleteAddress.php")
    Call<ServerResponse> deleteAddress(@Query("idCustomer") int id, @Query("idAddress") int idAddress);

    @GET("HealthSolutionsDB/ProductList.php")
    Call<ServerResponse> performGetList();

    @GET("HealthSolutionsDB/getListAddress.php")
    Call<ServerResponse> performGetAddressList(@Query("idCustomer") int id);

    @GET("HealthSolutionsDB/getIdProduct.php")
    Call<ServerResponse> performGetIdProduct(@Query("idProduct") int idProduct);

}
