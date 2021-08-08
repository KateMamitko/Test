package com.example.test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.test.Activity.Retrofit.ApiFactory;
import com.example.test.Activity.Retrofit.ApiService;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityJokes extends AppCompatActivity {
    private RecyclerView recyclerViewJokes;
    ArrayList<String> result = new ArrayList<>();
    private JokesAdapter adapter;
    String value;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        recyclerViewJokes = findViewById(R.id.RecyclerViewJokes);
        recyclerViewJokes.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("categories")){
            category = intent.getStringExtra("categories");
        }
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        for (int i = 0; i < 15; i++) {
            i++;
            apiService.getJokes(category).enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    if (response.isSuccessful()) {
                        value = response.body().getUrl();
                    }
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Toast.makeText(ActivityJokes.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    result = new ArrayList<>();
                    result.add(value);
                    adapter = new JokesAdapter();
                    recyclerViewJokes.setAdapter(adapter);
                }
            });
        }

    }
}