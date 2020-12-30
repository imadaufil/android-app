package com.example.mysteriousapp.presentation.articledisplay.mostpopular.mapper;

import android.text.TextUtils;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.presentation.articledisplay.mostpopular.adapter.ArticleViewItem;

import java.util.ArrayList;
import java.util.List;

public class ArticleToViewModelMapper {

    private ArticleViewItem map(Article article) {
        ArticleViewItem articleViewItem = new ArticleViewItem();
        articleViewItem.setTitle(article.getTitle());
        articleViewItem.setUrlImage(article.getMultimedia().get(0).getUrl());
        return articleViewItem;
    }

    public List<ArticleViewItem> map(List<Article> articleList) {
        List<ArticleViewItem> articleViewItems = new ArrayList<>();
        for (Article article : articleList) {
            articleViewItems.add(map(article));
        }
        return articleViewItems;
    }
}
