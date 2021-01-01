package com.example.mysteriousapp.data.repository;

import android.util.Log;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;
import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.data.repository.local.ArticleLocalDataSource;
import com.example.mysteriousapp.data.repository.mapper.ArticleToArticleEntityMapper;
import com.example.mysteriousapp.data.repository.remote.ArticleRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;

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
        return articleRemoteDataSource.getMostPopularArticles(apiKey)
                .zipWith(articleLocalDataSource.getSavedForLaterList(), new BiFunction<ArticlesHomeResponse, List<String>, ArticlesHomeResponse>() {
                    @NonNull
                    @Override
                    public ArticlesHomeResponse apply(@NonNull ArticlesHomeResponse articlesHomeResponse, @NonNull List<String> strings) throws Exception {
                        for (Article article : articlesHomeResponse.getResults()) {
                            if (strings.contains(article.getId()))
                                article.setSavedForLater();
                        }
                        return articlesHomeResponse;
                    }
                });
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
