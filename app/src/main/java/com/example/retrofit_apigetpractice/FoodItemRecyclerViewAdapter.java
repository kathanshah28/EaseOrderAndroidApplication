package com.example.retrofit_apigetpractice;

import static android.content.Context.MODE_PRIVATE;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodItemRecyclerViewAdapter extends RecyclerView.Adapter<FoodItemRecyclerViewAdapter.FoodItemViewHolder> {


    Map<String, Integer> cartDataItems;
    List<Food> fooditemList;
    Context context;

    public FoodItemRecyclerViewAdapter(List<Food> fooditemList, Context context,Map<String, Integer> cartDataItems) {
        this.fooditemList = fooditemList;
        this.context = context;
        this.cartDataItems = cartDataItems;
    }

    public void setCartDataItems(Map<String, Integer> cartDataItems){
        this.cartDataItems = cartDataItems;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fooditemslist,parent,false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        Food food = fooditemList.get(position);
        Picasso.get().load(food.getImageurl()).into(holder.foodItemImg);
        holder.fIName.setText(food.getName());
        holder.fIPrice.setText("$" + String.valueOf(food.getPrice()));
        String fICount = String.valueOf(cartDataItems.get(food.getId()));
        if(fICount == "null"){
            holder.fICount.setText(String.valueOf(0));
        }else{
            holder.fICount.setText(String.valueOf(cartDataItems.get(food.getId())));
        }
        holder.addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodItem(new AddRemoveBodyModel(food.getId()));
                notifyDataSetChanged();
            }
        });
        holder.removeFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFoodItem(new AddRemoveBodyModel(food.getId()));
                notifyDataSetChanged();
            }
        });

    }

    private void removeFoodItem(AddRemoveBodyModel RemoveBodyModel) {
        Retrofit removeFoodItemretrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/cart/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AddCartItemJsonPlaceHolder removeCartItemJsonPlaceHolder = removeFoodItemretrofit.create(AddCartItemJsonPlaceHolder.class);

        SharedPreferences loginPreferences = context.getSharedPreferences("LoginToken", MODE_PRIVATE);
        String loginToken = loginPreferences.getString("LoginId", null);

        Call<AddRemoveResponseModel> removeItemCall = removeCartItemJsonPlaceHolder.removeCartItem(loginToken,RemoveBodyModel);

        removeItemCall.enqueue(new Callback<AddRemoveResponseModel>() {
            @Override
            public void onResponse(Call<AddRemoveResponseModel> call, Response<AddRemoveResponseModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                cartDataItems.putAll(response.body().addRemoveDataModel().getCartData());
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AddRemoveResponseModel> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(context, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addFoodItem(AddRemoveBodyModel addBodymodel){
        Retrofit addFoodItemretrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/cart/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AddCartItemJsonPlaceHolder addCartItemJsonPlaceHolder = addFoodItemretrofit.create(AddCartItemJsonPlaceHolder.class);

        SharedPreferences loginPreferences = context.getSharedPreferences("LoginToken", MODE_PRIVATE);
        String loginToken = loginPreferences.getString("LoginId", null);

        Call<AddRemoveResponseModel> addItemCall = addCartItemJsonPlaceHolder.addCartItem(loginToken,addBodymodel);

        addItemCall.enqueue(new Callback<AddRemoveResponseModel>() {
            @Override
            public void onResponse(Call<AddRemoveResponseModel> call, Response<AddRemoveResponseModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                cartDataItems.putAll(response.body().addRemoveDataModel().getCartData());
                notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<AddRemoveResponseModel> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(context, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getFoodItemCount(String id){
        return 3;
    }

    public void getCartData(){
        Retrofit cartDataretrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:4000/api/v1/cart/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartResponseJsonPlaceHolder cartResponseJsonPlaceHolder = cartDataretrofit.create(CartResponseJsonPlaceHolder.class);

        SharedPreferences loginPreferences = context.getSharedPreferences("LoginToken", MODE_PRIVATE);
        String loginToken = loginPreferences.getString("LoginId", null);

        Call<CartItemsReponse> cartItemCall = cartResponseJsonPlaceHolder.getCartData(loginToken);
        cartItemCall.enqueue(new Callback<CartItemsReponse>() {
            @Override
            public void onResponse(Call<CartItemsReponse> call, Response<CartItemsReponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                assert response.body() != null;
                cartDataItems.putAll(response.body().getCartData());
//                Map<String, Integer> cartItemData = response.body().getCartData();
                for (Map.Entry<String, Integer> entry : cartDataItems.entrySet()) {
                    Log.d("CartDataItemsrecyclerview", "ID: " + entry.getKey() + " Quantity: " + entry.getValue());
                    // You can store or process these key-value pairs as needed
                }
            }

            @Override
            public void onFailure(Call<CartItemsReponse> call, Throwable throwable) {
                Log.d("ftechfailed",throwable.toString());
                Toast.makeText(context, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fooditemList.size();
    }

    public void updateFoodList(List<Food> newFoodList) {
        fooditemList.clear();
        fooditemList.addAll(newFoodList);
        notifyDataSetChanged();
    }

    public class FoodItemViewHolder extends RecyclerView.ViewHolder{
        ImageView foodItemImg;
        ImageButton addFoodButton,removeFoodButton;
        TextView fIName,fIPrice,fICount;
        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodItemImg = itemView.findViewById(R.id.foodiimage);
            fIName = itemView.findViewById(R.id.finame);
            fIPrice = itemView.findViewById(R.id.fiprice);
            fICount = itemView.findViewById(R.id.itemCountTextView);
            addFoodButton = itemView.findViewById(R.id.addItemButton);
            removeFoodButton = itemView.findViewById(R.id.removeItemButton);
        }
    }
}
