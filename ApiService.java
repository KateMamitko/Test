package com.example.test.Activity.Retrofit;

import com.example.test.Activity.Example;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET ("categories")
    Call<List<String>> getCategories();

    @GET ("/random?category={category}")
    Call <Example> getJokes(@Path("category") String category);
}
