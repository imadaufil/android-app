package com.example.mysteriousapp.presentation.article.saved_for_later.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mysteriousapp.R;
import com.example.mysteriousapp.presentation.article.ArticleActivity;

import java.util.ArrayList;
import java.util.List;

public class SavedForLaterAdapter extends RecyclerView.Adapter<SavedForLaterAdapter.SavedForLaterViewHolder> {

    private List<SavedForLaterViewItem> savedForLaterViewItemList;
    private SavedForLaterActionInterface savedForLaterActionInterface;

    public SavedForLaterAdapter( SavedForLaterActionInterface savedForLaterActionInterface) {
        savedForLaterViewItemList = new ArrayList<>();
        this.savedForLaterActionInterface = savedForLaterActionInterface;
    }

    public void bindViewModels(List<SavedForLaterViewItem> savedForLaterViewItemList) {
        this.savedForLaterViewItemList.clear();
        this.savedForLaterViewItemList.addAll(savedForLaterViewItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SavedForLaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conatiner_list, parent, false);
        SavedForLaterViewHolder savedForLaterViewHolder = new SavedForLaterViewHolder(view, savedForLaterActionInterface);
        return savedForLaterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedForLaterViewHolder holder,final int position) {
        holder.bind(savedForLaterViewItemList.get(position));
        holder.articleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedForLaterActionInterface.onArticle(savedForLaterViewItemList.get(position).getTitle(),
                        savedForLaterViewItemList.get(position).getAbstract(), savedForLaterViewItemList.get(position).getThumbnailUrl(),
                        savedForLaterViewItemList.get(position).getCaption(), savedForLaterViewItemList.get(position).getCopyright(), savedForLaterViewItemList.get(position).getByline(),
                        savedForLaterViewItemList.get(position).getUrl());

            }
        });
    }

    @Override
    public int getItemCount() {
        return savedForLaterViewItemList.size();
    }

    public static class SavedForLaterViewHolder extends RecyclerView.ViewHolder {
        private SavedForLaterActionInterface savedForLaterActionInterface;
        private View view;
        private ConstraintLayout articleLayout;
        private ImageView articleThumbnail;
        private TextView articleTitle;
        private TextView articleImgCopyright;
        private CheckBox savedForLater;
        private SavedForLaterViewItem savedForLaterViewItem;

        public SavedForLaterViewHolder(@NonNull View itemView, final SavedForLaterActionInterface savedForLaterActionInterface) {
            super(itemView);
            view = itemView;
            articleTitle = view.findViewById(R.id.articleTitle);
            articleThumbnail = view.findViewById(R.id.articleThumbnail);
            savedForLater = view.findViewById(R.id.savedForLater);
            articleLayout = view.findViewById(R.id.articleLayout);
            articleImgCopyright = itemView.findViewById(R.id.articleImgCopyright);
            setupListeners();
            this.savedForLaterActionInterface = savedForLaterActionInterface;
        }

        private void setupListeners() {
            savedForLater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!isChecked)
                        savedForLaterActionInterface.onRemoveSavedForLater(savedForLaterViewItem.getId());


                }
            });
        }

        void bind(SavedForLaterViewItem savedForLaterViewItem){
            this.savedForLaterViewItem = savedForLaterViewItem;
            articleTitle.setText(savedForLaterViewItem.getTitle());
            Glide.with(view)
                    .load(savedForLaterViewItem.getThumbnailUrl())
                    .into(articleThumbnail);
            savedForLater.setChecked(true);
            articleImgCopyright.setText(savedForLaterViewItem.getCopyright());
        }
    }
}
