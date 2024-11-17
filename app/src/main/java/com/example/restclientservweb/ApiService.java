package com.example.restclientservweb;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/dsaApp/users/register2")
    Call<User> registerUser(@Body User user);

    @POST("/dsaApp/users/login2")
    Call<User> loginUser(@Body User user);

    @GET("/dsaApp/objects/Android")
    Call<List<Products>> getProducts();

   @GET("/dsaApp/users/{username}/dinero")
    Call<Integer> getDinero(@Path("username") String username);

    @POST("/dsaApp/users/{username}/products/{id}")
    Call<Products> addProductToUser(@Path("username") String username, @Path("id") int id);
}
