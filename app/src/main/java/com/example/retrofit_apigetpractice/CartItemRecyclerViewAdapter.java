package com.example.retrofit_apigetpractice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class CartItemRecyclerViewAdapter extends RecyclerView.Adapter<CartItemRecyclerViewAdapter.CartItemsViewHolder> {

    Context context;
    List<Food> foodLists;
    Map<String, Integer> cartDataItems;

    public CartItemRecyclerViewAdapter(Context context, List<Food> foodLists, Map<String, Integer> cartDataItems) {
        this.context = context;
        this.foodLists = foodLists;
        this.cartDataItems = cartDataItems;
    }

    @NonNull
    @Override
    public CartItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cartitemlistview,parent,false);
        return new CartItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemsViewHolder holder, int position) {
        Food fooditem = foodLists.get(position);

        if(cartDataItems.containsKey(fooditem.getId()) && cartDataItems.get(fooditem.getId())>0){
            Log.d("contains key",fooditem.getId());
            Picasso.get().load(fooditem.getImageurl()).into(holder.foodImage);
            holder.foodTitle.setText(fooditem.getName());
            holder.foodPrice.setText("$" + String.valueOf(fooditem.getPrice()));
            holder.foodQuantity.setText(String.valueOf(cartDataItems.get(fooditem.getId())));
            holder.foodTotal.setText(String.valueOf(fooditem.getPrice() * cartDataItems.get(fooditem.getId())));
        }else{
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return cartDataItems.size();
    }

    public void updateFoodList(List<Food> filteredFoodItems) {
        this.foodLists = filteredFoodItems;
    }

    public class CartItemsViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView foodTitle,foodPrice,foodQuantity,foodTotal;
        ImageButton foodRemoveButton;

        public CartItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.CartFoodItemImage);
            foodTitle = itemView.findViewById(R.id.CartItemTitle);
            foodPrice = itemView.findViewById(R.id.CartItemPrice);
            foodQuantity = itemView.findViewById(R.id.CartItemQuantity);
            foodTotal = itemView.findViewById(R.id.CartItemTotal);
            foodRemoveButton = itemView.findViewById(R.id.removeItemButton);
        }
    }
}
