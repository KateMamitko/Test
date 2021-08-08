package com.example.test.Activity.Retrofit;

import com.google.gson.JsonObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static ApiFactory apiFactory;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.chucknorris.io/jokes/";


    public static ApiFactory getInstance() {
        if (apiFactory == null){
            apiFactory = new ApiFactory();
        }
        return apiFactory;
    }

    public ApiFactory() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }


    public ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }

}
