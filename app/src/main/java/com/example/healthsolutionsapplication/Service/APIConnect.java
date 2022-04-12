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
    Call<ServerResponse> performRegister(@Query("name") String name, @Query("account") String account,
                                         @Query("password") String password, @Query("phone") String phone);


    @GET("HealthSolutionsDB/Login.php")
    Call<ServerResponse> performLogin(@Query("account") String account, @Query("password") String password);

    @GET("HealthSolutionsDB/UpdateName.php")
    Call<ServerResponse> performName(@Query("id") int id, @Query("name") String name);

    @GET("HealthSolutionsDB/updateEmail.php")
    Call<ServerResponse> performEmail(@Query("id") int id, @Query("email") String email);

    @GET("HealthSolutionsDB/updatePhone.php")
    Call<ServerResponse> performPhone(@Query("id") int id, @Query("phone") String phone);

    @GET("HealthSolutionsDB/updateGender.php")
    Call<ServerResponse> performGender(@Query("id") int id, @Query("gender") int gender);

    @GET("HealthSolutionsDB/getIdCustomer.php")
    Call<ServerResponse> getIdCustomer(@Query("id") int id);

    @GET("HealthSolutionsDB/updatePassword.php")
    Call<ServerResponse> performPassword(@Query("id") int id, @Query("password") String password);

    @GET("HealthSolutionsDB/updateDob.php")
    Call<ServerResponse> performDob(@Query("id") int id, @Query("dob") String dob);

    @GET("HealthSolutionsDB/ProductList.php")
    Call<ServerResponse> performGetList();

    @GET("HealthSolutionsDB/insertAddress.php")
    Call<ServerResponse> performAddress(@Query("id") int id, @Query("_address") String address,
                                        @Query("_isDefault") boolean isDefault);

    @GET("HealthSolutionsDB/updateAddress.php")
    Call<ServerResponse> updateAddress(@Query("id") int id, @Query("_address") String address,
                                        @Query("_isDefault") boolean isDefault);

    @GET("HealthSolutionsDB/getListAddress.php")
    Call<ServerResponse> performGetAddressList(@Query("id") int id);

    @GET("HealthSolutionsDB/getIdProduct.php")
    Call<ServerResponse> performGetIdProduct(@Query("Ids") int Ids);

}
