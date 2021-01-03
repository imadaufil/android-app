package com.example.mysteriousapp.data.repository.mapper;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.entity.ArticleEntity;

public class ArticleToArticleEntityMapper {

    public ArticleEntity map(Article article) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(article.getId());
        articleEntity.setTitle(article.getTitle());
        articleEntity.setSummary(article.getSummary());
        articleEntity.setUrl(article.getUrl());
        articleEntity.setByline(article.getByline());
        articleEntity.setPublished_date(article.getPublished_date());
        articleEntity.setThumbnailUrl(article.getMultimedia().get(0).getUrl());
        articleEntity.setCaption(article.getMultimedia().get(0).getCaption());
        articleEntity.setCopyright(article.getMultimedia().get(0).getCopyright());
        articleEntity.setFormat(article.getMultimedia().get(0).getFormat());
        return articleEntity;
    }
}
