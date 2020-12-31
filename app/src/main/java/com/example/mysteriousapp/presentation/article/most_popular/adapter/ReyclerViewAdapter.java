package com.example.mysteriousapp.presentation.article.most_popular.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.api.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ReyclerViewAdapter extends RecyclerView.Adapter<ReyclerViewAdapter.ViewHolder> {

    private List<Article> articles = new ArrayList<>();
    private Context context;

    public ReyclerViewAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        /*
        Glide.with(context)
                .asBitmap()
                .load(articles.get(position).getThumbnailUrl())
                .into(holder.articleThumbnail);
        holder.articleTitle.setText(articles.get(position).getTitle());
        holder.articleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "toast");
                Toast.makeText(context, articles.get(position).getTitle().substring(0, 10), Toast.LENGTH_SHORT);
            }
        });

         */
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView articleThumbnail;
        TextView articleTitle;
        ConstraintLayout articleLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleThumbnail = itemView.findViewById(R.id.articleThumbnail);
            articleTitle = itemView.findViewById(R.id.articleTitle);
            articleLayout = itemView.findViewById(R.id.articleLayout);
        }
    }
}
