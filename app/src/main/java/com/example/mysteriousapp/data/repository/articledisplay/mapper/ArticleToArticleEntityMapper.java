package com.example.mysteriousapp.data.repository.articledisplay.mapper;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.entity.ArticleEntity;

public class ArticleToArticleEntityMapper {

    public ArticleEntity map(Article article) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setByline(article.getByline());
        articleEntity.setMultimedia(article.getMultimedia());
        articleEntity.setPublished_date(article.getPublished_date());
        articleEntity.setTitle(article.getTitle());
        articleEntity.setUri(article.getId());
        articleEntity.setSummary(article.getSummary());
        articleEntity.setSection(article.getSection());
        articleEntity.setUrl(article.getUrl());
        return articleEntity;
    }
}
