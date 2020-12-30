package com.example.mysteriousapp.data.repository.articledisplay.remote;

import com.example.mysteriousapp.StoriesApplication;
import com.example.mysteriousapp.data.api.ArticleService;
import com.example.mysteriousapp.data.api.model.ArticleSearchResponse;

import io.reactivex.Single;

public class ArticleRemoteDataSource {
    private ArticleService articleService;

    public ArticleRemoteDataSource(ArticleService articleService) {
        this.articleService = articleService;
    }

    // getArticles addArticleForSavedForLater
    public Single<ArticleSearchResponse> getArticles() {
        return articleService.getHome(StoriesApplication.API_KEY);
    }
}
