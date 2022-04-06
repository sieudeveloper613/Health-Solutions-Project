package com.example.healthsolutionsapplication.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {
//    @GET("test/getall_product.php")
//    Call<ServerResponse> getall_product (@Query("name") String name, @Query("categoryName") String categoryName,
//                                 @Query("typeOfBird") String typeOfBird, @Query("backgroundBird") String BackgroundBird,
//                                 @Query("image") String image, @Query("price") double price,
//                                 @Query("locatedOfBird") String locatedOfBird, @Query("whereProduct") String whereProduct,
//                                 @Query("branchProduct") String branchProduct );
    @GET("test/getall_product.php")
    Call<ServerResponse> getall_product();
}
