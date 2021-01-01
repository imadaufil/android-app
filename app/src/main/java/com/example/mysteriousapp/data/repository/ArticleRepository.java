package com.example.mysteriousapp.data.repository;

import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;
import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.data.repository.local.ArticleLocalDataSource;
import com.example.mysteriousapp.data.repository.mapper.ArticleToArticleEntityMapper;
import com.example.mysteriousapp.data.repository.remote.ArticleRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class ArticleRepository {
    private ArticleRemoteDataSource articleRemoteDataSource;
    private ArticleLocalDataSource articleLocalDataSource;
    private ArticleToArticleEntityMapper articleToArticleEntityMapper;

    public ArticleRepository(ArticleRemoteDataSource articleRemoteDataSource, ArticleLocalDataSource articleLocalDataSource, ArticleToArticleEntityMapper articleToArticleEntityMapper) {
        this.articleRemoteDataSource = articleRemoteDataSource;
        this.articleLocalDataSource = articleLocalDataSource;
        this.articleToArticleEntityMapper = articleToArticleEntityMapper;
    }

    public Single<ArticlesHomeResponse> getMostPopularArticles(String apiKey) {
        return articleRemoteDataSource.getMostPopularArticles(apiKey);
    }

    public Completable addArticleToSavedToLater(ArticleEntity articleEntity) {
        return articleLocalDataSource.addArticleToSavedForLater(articleEntity);
    }

    public Completable deleteArticleFromSavedForLater(String id) {
        return articleLocalDataSource.deleteArticleToSavedForLater(id);
    }

    public Flowable<List<ArticleEntity>> getSavedForLater() {
        return articleLocalDataSource.loadSavedForLater();
    }
}
