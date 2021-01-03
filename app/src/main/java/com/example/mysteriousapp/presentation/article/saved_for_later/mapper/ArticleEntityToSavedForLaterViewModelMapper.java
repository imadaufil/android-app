package com.example.mysteriousapp.presentation.article.saved_for_later.mapper;

import android.util.Log;

import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.presentation.article.saved_for_later.adapter.SavedForLaterViewItem;

import java.util.ArrayList;
import java.util.List;

public class ArticleEntityToSavedForLaterViewModelMapper {

    private SavedForLaterViewItem map(ArticleEntity article) {
        SavedForLaterViewItem articleViewItem = new SavedForLaterViewItem();
        articleViewItem.setTitle(article.getTitle());
        articleViewItem.setAbstract(article.getSummary());
        articleViewItem.setThumbnailUrl(article.getThumbnailUrl());
        articleViewItem.setId(article.getId());
        articleViewItem.setUrl(article.getUrl());
        articleViewItem.setByline(article.getByline());
        articleViewItem.setPublished_date(article.getPublished_date());
        articleViewItem.setCaption(article.getCaption());
        articleViewItem.setCopyright(article.getCopyright());
        articleViewItem.setFormat(article.getFormat());
        return articleViewItem;
    }

    public List<SavedForLaterViewItem> map(List<ArticleEntity> articleList){
        List<SavedForLaterViewItem> articleViewItemList = new ArrayList<>();
        for(ArticleEntity article : articleList) {
            articleViewItemList.add(map(article));
        }
        return articleViewItemList;
    }

}
