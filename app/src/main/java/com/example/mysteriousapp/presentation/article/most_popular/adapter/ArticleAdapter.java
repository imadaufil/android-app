package com.example.mysteriousapp.presentation.article.most_popular.adapter;

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

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<ArticleViewItem> articleViewItemList;
    private ArticleActionInterface articleActionInterface;

    public ArticleAdapter(ArticleActionInterface articleActionInterface) {
        articleViewItemList = new ArrayList<>();
        this.articleActionInterface = articleActionInterface;
    }

    public void bindViewModels(List<ArticleViewItem> articleViewItemList) {
        this.articleViewItemList.clear();
        this.articleViewItemList.addAll(articleViewItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_article, parent, false);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(view);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        holder.bind(articleViewItemList.get(position));
        holder.articleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                articleActionInterface.onArticle(articleViewItemList.get(position).getTitle(), articleViewItemList.get(position).getAbstract(), articleViewItemList.get(position).getThumbnailUrl());

                //Toast.makeText(v.getContext(), articleViewItemList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleViewItemList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView articleThumbnail;
        TextView articleTitle;
        ConstraintLayout articleLayout;
        View view;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            articleThumbnail = itemView.findViewById(R.id.articleThumbnail);
            articleTitle = itemView.findViewById(R.id.articleTitle);
            articleLayout = itemView.findViewById(R.id.articleLayout);
        }


        void bind(ArticleViewItem articleViewItem) {
            articleTitle.setText(articleViewItem.getTitle());
            Glide.with(view)
                    .load(articleViewItem.getThumbnailUrl())
                    .into(articleThumbnail);
        }

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
}
