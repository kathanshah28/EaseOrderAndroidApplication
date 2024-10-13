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

public class OrderHistoryListRecycleViewAdapter extends RecyclerView.Adapter<OrderHistoryListRecycleViewAdapter.OrderHistoryListViewHolder> {

    Context context;
    List<OrderItemsModel> orderItemsList;

    public OrderHistoryListRecycleViewAdapter(Context context, List<OrderItemsModel> orderItemsList) {
        this.context = context;
        this.orderItemsList = orderItemsList;
    }

    @NonNull
    @Override
    public OrderHistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderhistoryitemlistview,parent,false);
        return new OrderHistoryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryListViewHolder holder, int position) {
        OrderItemsModel orderItem = orderItemsList.get(position);
        holder.foodTitle.setText(orderItem.getFoodName());
        holder.foodPrice.setText("$ " + String.valueOf(orderItem.getPrice()));
        holder.foodQuantity.setText(orderItem.getQuantity());
        holder.foodTotal.setText("$ " + String.valueOf(((Integer.parseInt(orderItem.getQuantity())) * orderItem.getPrice())));
        holder.foodStatus.setText(orderItem.getStatus());

        Picasso.get().load(orderItem.getImageUrl()).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return orderItemsList.size();
    }

    public class OrderHistoryListViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView foodTitle,foodPrice,foodQuantity,foodTotal,foodStatus;

        public OrderHistoryListViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.OrderHistoryFoodItemImage);
            foodTitle = itemView.findViewById(R.id.OrderHistoryItemTitle);
            foodPrice = itemView.findViewById(R.id.OrderHistoryItemPrice);
            foodQuantity = itemView.findViewById(R.id.OrderHistoryItemQuantity);
            foodTotal = itemView.findViewById(R.id.OrderHistoryItemTotal);
            foodStatus = itemView.findViewById(R.id.OrderHistoryItemStatus);

        }
    }
}
