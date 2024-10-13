package com.example.retrofit_apigetpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class foodlist extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Food> foodList;
    FloatingActionButton menuChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        menuChange = findViewById(R.id.MenuChange);

        foodList = new ArrayList<>();

        recyclerView = findViewById(R.id.foodrecyclerview);
        recyclerView.setHasFixedSize(true);

        Retrofit foodretrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/food/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodJsonPlaceHolder foodJsonPlaceHolder = foodretrofit.create(FoodJsonPlaceHolder.class);
        Call<FoodResponse> foodcall = foodJsonPlaceHolder.getFoodList();

        foodcall.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(foodlist.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                List<Food> foodlist = response.body().getFooddata();
                for(Food food1 : foodlist){
                    Log.d("name",food1.getName());
                    Log.d("id",food1.getId());
                    Log.d("desc",food1.getDescription());
                    Log.d("imgurl",food1.getImageurl());
                    Log.d("category",food1.getCategory());
                    Log.d("price","price:"+food1.getPrice());
                    foodList.add(new Food(food1.getId(),food1.getName(),food1.getDescription(),food1.getImageurl(),food1.getCategory(),food1.getPrice()));
                }
                FoodAdapter foodAdapter = new FoodAdapter(foodList, foodlist.this);
                recyclerView.setAdapter(foodAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(foodlist.this));
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(foodlist.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
        Intent iNext;
        iNext = new Intent(foodlist.this,LandingPage.class);
        menuChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iNext);
            }
        });
    }
}