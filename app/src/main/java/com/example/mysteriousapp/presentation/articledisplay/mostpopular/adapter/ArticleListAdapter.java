package com.example.mysteriousapp.presentation.articledisplay.mostpopular.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.mysteriousapp.R;
import com.example.mysteriousapp.data.api.model.Article;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;
        private Button buttonFav;
        private ArticleViewItem articleViewItem;
        private View view;
        private ArticleActionInterface articleActionInterface;

        private final String m_msgAddFav = "Ajout aux favoris";
        private final String m_msgAlreadyAdd = "Déja ajouté";

        public ArticleViewHolder(@NonNull View itemView, ArticleActionInterface articleActionInterface) {
            super(itemView);
            this.articleActionInterface = articleActionInterface;
            title = itemView.findViewById(R.id.textTitlte);
            image = itemView.findViewById(R.id.imageLayout);
            view = itemView;
            buttonFav = itemView.findViewById(R.id.button_fav);
        }

        public void bind(final ArticleViewItem articleViewItem) {
            this.articleViewItem = articleViewItem;
            title.setText(articleViewItem.getTitle());
            Glide.with(view)
                    .load(articleViewItem.getUrlImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image);
            this.view.findViewById(R.id.button_fav).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(articleViewItem.isSavedForLater()) {
                        Snackbar.make(view, m_msgAlreadyAdd, Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    articleActionInterface.onFav(articleViewItem.getTitle(), articleViewItem.getUrlImage());
                    Snackbar.make(view, m_msgAddFav, Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    private ArrayList<ArticleViewItem> articles;
    private ArticleActionInterface articleActionInterface;

    public ArticleListAdapter(ArticleActionInterface articleActionInterface) {
        articles = new ArrayList<>();
        this.articleActionInterface = articleActionInterface;
    }

    public void bindViewModels(List<ArticleViewItem> articleViewItems) {
        this.articles.clear();
        this.articles.addAll(articleViewItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_article, parent, false);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(v, articleActionInterface);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
