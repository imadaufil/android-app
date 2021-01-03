package com.example.mysteriousapp.presentation.article.articles.mapper;

import android.util.Log;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleViewItem;

import java.util.ArrayList;
import java.util.List;

public class ArticleToViewModelMapper {

    private ArticleViewItem map(Article article) {
        Log.d("TEST", "ID : " + article.getId() + " URL : " +  article.getUrl());
        ArticleViewItem articleViewItem = new ArticleViewItem();
        articleViewItem.setTitle(article.getTitle());
        articleViewItem.setAbstract(article.getSummary());
        articleViewItem.setThumbnailUrl(article.getMultimedia().get(0).getUrl());
        articleViewItem.setId(article.getId());
        articleViewItem.setUrl(article.getUrl());
        articleViewItem.setByline(article.getByline());
        articleViewItem.setPublished_date(article.getPublished_date());
        articleViewItem.setCaption(article.getMultimedia().get(0).getCaption());
        articleViewItem.setCopyright(article.getMultimedia().get(0).getCopyright());
        articleViewItem.setFormat(article.getMultimedia().get(0).getFormat());
        articleViewItem.setSavedForLater(article.isSavedForLater());
        return articleViewItem;
    }

    public List<ArticleViewItem> map(List<Article> articleList){
        List<ArticleViewItem> articleViewItemList = new ArrayList<>();
        for(Article article : articleList) {
            articleViewItemList.add(map(article));
        }
        return articleViewItemList;
    }
}
