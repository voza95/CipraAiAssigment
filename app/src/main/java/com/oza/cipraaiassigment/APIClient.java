package com.oza.cipraaiassigment;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.cipra.ai:5000/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }
}
