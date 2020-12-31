package com.example.mysteriousapp.presentation.article.most_popular.mapper;

import android.util.Log;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleViewItem;

import java.util.ArrayList;
import java.util.List;

public class ArticleToViewModelMapper {

    private ArticleViewItem map(Article article) {
        ArticleViewItem articleViewItem = new ArticleViewItem();
        articleViewItem.setTitle(article.getTitle());
        articleViewItem.setAbstract(article.getSummary());
        articleViewItem.setThumbnailUrl(article.getMultimedia().get(0).getUrl());
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
