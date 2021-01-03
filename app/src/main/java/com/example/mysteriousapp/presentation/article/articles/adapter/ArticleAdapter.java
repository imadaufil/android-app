package com.example.mysteriousapp.presentation.article.articles.adapter;

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

import java.util.ArrayList;
import java.util.List;

/**
 * ArticleAdapter
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<ArticleViewItem> articleViewItemList;
    private ArticleActionInterface articleActionInterface;

    /**
     * Constructor
     * @param articleActionInterface article ActionInterface
     */
    public ArticleAdapter(ArticleActionInterface articleActionInterface) {
        articleViewItemList = new ArrayList<>();
        this.articleActionInterface = articleActionInterface;
    }

    /**
     * Bind data
     * @param articleViewItemList article ViewItem List
     */
    public void bindViewModels(List<ArticleViewItem> articleViewItemList) {
        this.articleViewItemList.clear();
        this.articleViewItemList.addAll(articleViewItemList);
        notifyDataSetChanged();
    }

    /**
     * on Create ViewHolder
     * @param parent parent
     * @param viewType viewType
     * @return
     */
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conatiner_list, parent, false);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(view, articleActionInterface);
        return articleViewHolder;
    }

    /**
     * on BindViewHolder
     * @param holder holder
     * @param position position
     */
    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        holder.bind(articleViewItemList.get(position));
        holder.articleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                articleActionInterface.onArticle(articleViewItemList.get(position).getTitle(),
                        articleViewItemList.get(position).getAbstract(),
                        articleViewItemList.get(position).getThumbnailUrl(),
                        articleViewItemList.get(position).getCaption(), articleViewItemList.get(position).getCopyright(), articleViewItemList.get(position).getByline(),
                        articleViewItemList.get(position).getUrl());
            }
        });

    }

    /**
     * get articles count
     * @return articles size
     */
    @Override
    public int getItemCount() {
        return articleViewItemList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView articleThumbnail;
        TextView articleTitle;
        TextView articleImgCopyright;
        ConstraintLayout articleLayout;
        CheckBox savedForLater;
        View view;
        ArticleViewItem articleViewItem;
        ArticleActionInterface articleActionInterface;

        /**
         * ArticleViewHolder
         * @param itemView itemView
         * @param articleActionInterface articleActionInterface
         */
        public ArticleViewHolder(@NonNull View itemView, final ArticleActionInterface articleActionInterface) {
            super(itemView);
            view = itemView;
            articleThumbnail = itemView.findViewById(R.id.articleThumbnail);
            articleTitle = itemView.findViewById(R.id.articleTitle);
            articleLayout = itemView.findViewById(R.id.articleLayout);
            savedForLater = itemView.findViewById(R.id.savedForLater);
            articleImgCopyright = itemView.findViewById(R.id.articleImgCopyright);
            this.articleActionInterface = articleActionInterface;
            setupListeners();
        }

        /**
         * setup isteners
         */
        private void setupListeners() {
            savedForLater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    articleActionInterface.onSavedForLaterToggle(articleViewItem.getId(), articleViewItem.getTitle(), articleViewItem.getSummary()
                            , articleViewItem.getUrl(), articleViewItem.getByline(), articleViewItem.getPublished_date(), articleViewItem.getThumbnailUrl(), articleViewItem.getCaption()
                            , articleViewItem.getCopyright(), articleViewItem.getFormat(), isChecked);
                    articleViewItem.setSavedForLater(isChecked);
                }
            });
        }


        /**
         * bind data
         * @param articleViewItem articleViewItem
         */
        void bind(ArticleViewItem articleViewItem) {
            this.articleViewItem = articleViewItem;
            articleTitle.setText(articleViewItem.getTitle());
            Glide.with(view)
                    .load(articleViewItem.getThumbnailUrl())
                    .into(articleThumbnail);
            savedForLater.setChecked(articleViewItem.isSavedForLater());
            articleImgCopyright.setText(articleViewItem.getCopyright());
        }
    }
}
