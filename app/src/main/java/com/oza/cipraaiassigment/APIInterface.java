package com.oza.cipraaiassigment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("takehome/signin")
    Call<String> signIn(@Query("email") String email, @Query("password") String password);

}
