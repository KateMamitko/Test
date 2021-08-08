package com.example.test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.example.test.Activity.Retrofit.ApiFactory;
import com.example.test.Activity.Retrofit.ApiService;
import com.example.test.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activityListOfCategories extends AppCompatActivity {
    private RecyclerView recyclerViewCategories;
    private JokesAdapter adapter;
    private List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_categories);
        recyclerViewCategories = findViewById(R.id.RecyclerViewCategories);
        Intent intent = getIntent();
        categories = new ArrayList<>();
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        apiService.getCategories().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()){
                    categories = response.body();
                        adapter = new JokesAdapter(categories);
                        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(activityListOfCategories.this));
                        recyclerViewCategories.setAdapter(adapter);
                        adapter.setOnClickListener(new JokesAdapter.onClickListener() {
                            @Override
                            public void onClick(int position) {
                                Intent intent1 = new Intent(activityListOfCategories.this,ActivityJokes.class);
                                String name = categories.get(position);
                                intent1.putExtra("categories",name);
                                startActivity(intent1);
                            }
                        });

                    }
                }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(activityListOfCategories.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}