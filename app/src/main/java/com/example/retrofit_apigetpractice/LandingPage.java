package com.example.retrofit_apigetpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingPage extends AppCompatActivity {

    AppBarLayout appBarLayout;
    Toolbar toolbar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("EaseOrder");
        toolbar.setLogo(R.drawable.food);

        toolbar.inflateMenu(R.menu.toolbarmenu);

        Menu toolbarMenu = toolbar.getMenu();

        MenuItem loginItem = toolbarMenu.findItem(R.id.Login);
        MenuItem orderHistoryItem = toolbarMenu.findItem(R.id.OrderHistory);

        // Get the stored login token
        SharedPreferences loginPreferences = getSharedPreferences("LoginToken", MODE_PRIVATE);
        String loginToken = loginPreferences.getString("LoginId", null);


        // If the login token is not null, user is logged in
        boolean isLoggedIn = loginToken != null;

        Log.d("isLoggedIn",String.valueOf(isLoggedIn));


        loginItem.setVisible(!isLoggedIn);
        orderHistoryItem.setVisible(isLoggedIn);


        // Call invalidateOptionsMenu to update the menu items based on login status ChatGPT
        invalidateOptionsMenu();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.toolbarmenuframe,new HomeFragment());
        ft.commit();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent iNext;
                int id= item.getItemId();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                    if (id==R.id.Login){
                        Dialog loginDialog = new Dialog(LandingPage.this);
                        loginDialog.setContentView(R.layout.logindialogbox);
                        EditText restaurantName_Edit = loginDialog.findViewById(R.id.Restaurant_NameEdit);
                        EditText tableNo_Edit = loginDialog.findViewById(R.id.Table_NoEdit);
                        AppCompatButton loginBtn = loginDialog.findViewById(R.id.LoginBtn);
                        loginDialog.show();
                        loginBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                postData(restaurantName_Edit.getText().toString(),Integer.parseInt(tableNo_Edit.getText().toString()));
                                loginDialog.dismiss();
                            }
                        });
                    }
                    else if (id==R.id.OrderHistory) {
                        ft.replace(R.id.toolbarmenuframe,new orderHistoryFragment());
                        ft.commit();
//                        iNext = new Intent(LandingPage.this, orderhistory.class);
//                        startActivity(iNext);
                    } else if (id==R.id.Cart) {
                        ft.replace(R.id.toolbarmenuframe,new cartFragment());
                        ft.commit();
                        Toast.makeText(LandingPage.this, "clicked item", Toast.LENGTH_SHORT).show();
                    } else if (id == R.id.DarkMode) {
                        Toast.makeText(LandingPage.this, "clicked item", Toast.LENGTH_SHORT).show();
                    } else {
                        return false;
                    }
                return true;
            }

        });

    }

    private void postData(String retaurant_Name, int tableNo) {
        Retrofit loginRetroFit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/table/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginDataPostJsonPlaceHolder loginDataPostJsonPlaceHolder = loginRetroFit.create(LoginDataPostJsonPlaceHolder.class);
        Call<LoginDataResponse<LoginTokenModel>> loginDataCall = loginDataPostJsonPlaceHolder.loginTable(new LoginDataModel(retaurant_Name,tableNo));

        loginDataCall.enqueue(new Callback<LoginDataResponse<LoginTokenModel>>() {
            @Override
            public void onResponse(Call<LoginDataResponse<LoginTokenModel>> call, Response<LoginDataResponse<LoginTokenModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(LandingPage.this, "Details sent successfully!", Toast.LENGTH_SHORT).show();
                    assert response.body() != null;
                    LoginTokenModel LoginTokenModel = response.body().getData();
                    Log.d("LoginTokenModel",LoginTokenModel.toString());

                    String loginToken = LoginTokenModel.getLoginToken();
                    Log.d("LoginToken",loginToken);

                    // Store the login token in SharedPreferences
                    SharedPreferences loginPreferences = getSharedPreferences("LoginToken",MODE_PRIVATE);
                    SharedPreferences.Editor loginPrefEditor = loginPreferences.edit();
                    loginPrefEditor.putString("LoginId",loginToken);
                    loginPrefEditor.apply();
                    loginPrefEditor.commit();
                    // Update the menu
                    invalidateOptionsMenu();
                    recreate();
                } else {
                    Toast.makeText(LandingPage.this, "Failed to send details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginDataResponse<LoginTokenModel>> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(LandingPage.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });



    }

}

