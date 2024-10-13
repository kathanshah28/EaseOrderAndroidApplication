package com.example.retrofit_apigetpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton foodlistbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodlistbtn = findViewById(R.id.foodlistbtn);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent iNext;
        iNext = new Intent(MainActivity.this, foodlist.class);

        foodlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iNext);
            }
        });


        Retrofit jsonplaceholderretrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                                .build();



//        Retrofit foodretrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.5:4000/api/v1/food/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        FoodJsonPlaceHolder foodJsonPlaceHolder = foodretrofit.create(FoodJsonPlaceHolder.class);
//        Call<response> foodcall = foodJsonPlaceHolder.getFoodList();
//        foodcall.enqueue(new Callback<response>() {
//            @Override
//            public void onResponse(Call<response> call, Response<response> Response) {
//                if(!Response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, Response.code(), Toast.LENGTH_SHORT).show();
//                    return ;
//                }
//                response response = Response.body();
//                if (response != null && response.isSuccess()) {
//                    List<Food> foodList = response.getFooddata();
//                    Log.d("FoodData",foodList.toString());
//                    // Handle the list of food items
//                } else {
//                    // Handle unsuccessful response
//                }
//                Log.d("FoodResponse",Response.body().toString());
//            }
//            @Override
//            public void onFailure(Call<response> call, Throwable throwable) {
//                Log.d("ftechfailed",throwable.toString());
//                Toast.makeText(MainActivity.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
//            }
//        });
////        foodcall.enqueue(new Callback<LiFood>>() {
////            @Override
////            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
////                if(!response.isSuccessful()){
////                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
////                    return ;
////                }
////                Log.d("FoodResponse",response.body().toString());
////            }
////
////            @Override
////            public void onFailure(Call<List<Food>> call, Throwable throwable) {
////                Log.d("ftechfailed",throwable.toString());
////                Toast.makeText(MainActivity.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
////            }
////        });

        JSONPlaceHolder jsonPlaceHolder = jsonplaceholderretrofit.create(JSONPlaceHolder.class);
        Call<List<Post>> call = jsonPlaceHolder.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return ;
                }
                Log.d("JsonResponse",response.body().toString());
                List<Post> postList = response.body();
                PostAdapter postAdapter = new PostAdapter(postList,MainActivity.this);
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}