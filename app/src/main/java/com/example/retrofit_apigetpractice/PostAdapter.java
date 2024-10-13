package com.example.retrofit_apigetpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<Post> postList;
    Context context;

    public PostAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post post = postList.get(position);
        holder.id.setText(post.getId());
        holder.userid.setText(post.getUserid());
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView id,userid,title,body;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            id =itemView.findViewById(R.id.id);
            userid = itemView.findViewById(R.id.userid);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);

        }
    }
}
