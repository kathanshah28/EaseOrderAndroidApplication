package com.example.retrofit_apigetpractice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MenuCategoryAdapter extends RecyclerView.Adapter<MenuCategoryAdapter.MenuCategoryViewHolder> {

    List<MenuCategory> menuCategoryList;
    Context context;
    OnCategorySelectedListener listener;

    public MenuCategoryAdapter(List<MenuCategory> menuCategoryList, Context context) {
        this.menuCategoryList = menuCategoryList;
        this.context = context;
    }


    public MenuCategoryAdapter(List<MenuCategory> menuCategoryList, Context context,OnCategorySelectedListener listener) {
        this.menuCategoryList = menuCategoryList;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MenuCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_category_list,parent,false);
        return new MenuCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuCategoryViewHolder holder, int position) {
        MenuCategory menuCategory = menuCategoryList.get(position);
        holder.menuCategoryName.setText(menuCategory.getName());

        Picasso.get().load(menuCategory.getImageurl()).into(holder.menuCategoryImg);

        Intent iNext;

        holder.menuCategoryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences categoryPref = context.getSharedPreferences("Category",Context.MODE_PRIVATE);
                String category = categoryPref.getString("Category","All");
                Log.d("Category value at MEnuadapter",category);
                SharedPreferences.Editor categoryPrefEdit =categoryPref.edit();
                if(menuCategory.getName().equals(category)){
                    categoryPrefEdit.putString("Category","All");
                    listener.onCategorySelected("All");
                }
                else{
                    categoryPrefEdit.putString("Category",menuCategory.getName());
                    listener.onCategorySelected(menuCategory.getName());
                }
                categoryPrefEdit.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuCategoryList.size();
    }

    public class MenuCategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView menuCategoryImg;
        TextView menuCategoryName;
        LinearLayout menuCategoryLinearLayout;
        public MenuCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            menuCategoryImg = itemView.findViewById(R.id.MenuCategoryimage);
            menuCategoryName = itemView.findViewById(R.id.menucategory);
            menuCategoryLinearLayout = itemView.findViewById(R.id.categoryLinearLayout);
        }
    }
}
