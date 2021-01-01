package com.example.mysteriousapp.presentation.article.most_popular.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysteriousapp.R;
import com.example.mysteriousapp.presentation.article.ArticleActivity;

import java.util.ArrayList;
import java.util.List;

public class ArticleGridAdapter extends RecyclerView.Adapter<ArticleGridAdapter.ArticleGridViewHolder> {

    private List<ArticleViewItem> articleViewItemList;
    private ArticleActionInterface articleActionInterface;

    public ArticleGridAdapter(ArticleActionInterface articleActionInterface) {
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
    public ArticleGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_grid, parent, false);
        ArticleGridAdapter.ArticleGridViewHolder articleViewHolder = new ArticleGridAdapter.ArticleGridViewHolder(view, articleActionInterface);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleGridViewHolder holder, final int position) {
        holder.bind(articleViewItemList.get(position));
        holder.articleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // articleActionInterface.onArticle(articleViewItemList.get(position).getTitle(), articleViewItemList.get(position).getAbstract(), articleViewItemList.get(position).getThumbnailUrl());


                Intent intent = new Intent(v.getContext(), ArticleActivity.class);
                intent.putExtra("articleTitle", articleViewItemList.get(position).getTitle());
                intent.putExtra("articleAbstract", articleViewItemList.get(position).getAbstract());
                intent.putExtra("articleThumbnail", articleViewItemList.get(position).getThumbnailUrl());
                intent.putExtra("savedForLater", articleViewItemList.get(position).isSavedForLater());
                v.getContext().startActivity(intent);

                Toast.makeText(v.getContext(), articleViewItemList.get(position).isSavedForLater() ? "true" : "false", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleViewItemList.size();
    }

    public static class ArticleGridViewHolder extends RecyclerView.ViewHolder {

        ImageView articleThumbnail;
        TextView articleTitle;
        ConstraintLayout articleLayout;
        CheckBox savedForLater;
        View view;
        ArticleViewItem articleViewItem;
        ArticleActionInterface articleActionInterface;

        public ArticleGridViewHolder(@NonNull View itemView, ArticleActionInterface articleActionInterface) {
            super(itemView);
            view = itemView;
            articleThumbnail = itemView.findViewById(R.id.articleThumbnail);
            articleTitle = itemView.findViewById(R.id.articleTitle);
            articleLayout = itemView.findViewById(R.id.articleLayout);
            savedForLater = itemView.findViewById(R.id.savedForLater);
            this.articleActionInterface = articleActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            savedForLater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    /*
                    void onSavedForLaterToggle(String id, String title, String summary, String url,
                               String byline, String published_date, String thumbnailUrl, String caption,
                               String copyright, String format, boolean savedForLater);
                     */

                    articleActionInterface.onSavedForLaterToggle(articleViewItem.getId(), articleViewItem.getTitle(), articleViewItem.getSummary()
                            , articleViewItem.getUrl(), articleViewItem.getByline(), articleViewItem.getPublished_date(), articleViewItem.getThumbnailUrl(), articleViewItem.getCaption()
                            , articleViewItem.getCopyright(), articleViewItem.getFormat(), isChecked);
                    articleViewItem.setSavedForLater(isChecked);
                }
            });
        }

        void bind(ArticleViewItem articleViewItem) {
            this.articleViewItem = articleViewItem;
            articleTitle.setText(articleViewItem.getTitle());
            Glide.with(view)
                    .load(articleViewItem.getThumbnailUrl())
                    .into(articleThumbnail);
            savedForLater.setChecked(articleViewItem.isSavedForLater());

        }
    }
}
