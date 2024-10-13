package com.example.retrofit_apigetpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    List<Food> foodList;
    Context context;

    public FoodAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.foodlist,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.fname.setText(food.getName());
        holder.fdesc.setText(food.getDescription());
        holder.fcategory.setText(food.getCategory());
        holder.fprice.setText(String.valueOf(food.getPrice()));

        Picasso.get().load(food.getImageurl()).into(holder.fimage);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{

        TextView fname,fdesc,fcategory,fprice;

        ImageView fimage;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            fname = itemView.findViewById(R.id.fname);
            fdesc = itemView.findViewById(R.id.fdesc);
            fprice = itemView.findViewById(R.id.fprice);
            fcategory = itemView.findViewById(R.id.fcategory);
            fimage = itemView.findViewById(R.id.foodimage);

        }
    }
}
