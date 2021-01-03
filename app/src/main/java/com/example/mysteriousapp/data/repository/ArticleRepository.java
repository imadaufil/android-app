package com.example.mysteriousapp.data.repository;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.api.model.ArticlesResponse;
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

    public Single<ArticlesResponse> getHomeArticles(String apiKey) {
        return articleRemoteDataSource.getHomeArticles(apiKey)
                .zipWith(articleLocalDataSource.getSavedForLaterList(), new BiFunction<ArticlesResponse, List<String>, ArticlesResponse>() {
                    @NonNull
                    @Override
                    public ArticlesResponse apply(@NonNull ArticlesResponse articlesResponse, @NonNull List<String> strings) throws Exception {
                        for (Article article : articlesResponse.getResults()) {
                            if (strings.contains(article.getId()))
                                article.setSavedForLater();
                        }
                        return articlesResponse;
                    }
                });
    }

    public Single<ArticlesResponse> getTechnologyArticles(String apiKey) {
        return articleRemoteDataSource.getTechnologyArticles(apiKey)
                .zipWith(articleLocalDataSource.getSavedForLaterList(), new BiFunction<ArticlesResponse, List<String>, ArticlesResponse>() {
                    @NonNull
                    @Override
                    public ArticlesResponse apply(@NonNull ArticlesResponse articlesResponse, @NonNull List<String> strings) throws Exception {
                        for (Article article : articlesResponse.getResults()) {
                            if (strings.contains(article.getId()))
                                article.setSavedForLater();
                        }
                        return articlesResponse;
                    }
                });
    }

    public Single<ArticlesResponse> getSportsArticles(String apiKey) {
        return articleRemoteDataSource.getSportsArticles(apiKey)
                .zipWith(articleLocalDataSource.getSavedForLaterList(), new BiFunction<ArticlesResponse, List<String>, ArticlesResponse>() {
                    @NonNull
                    @Override
                    public ArticlesResponse apply(@NonNull ArticlesResponse articlesResponse, @NonNull List<String> strings) throws Exception {
                        for (Article article : articlesResponse.getResults()) {
                            if (strings.contains(article.getId()))
                                article.setSavedForLater();
                        }
                        return articlesResponse;
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
