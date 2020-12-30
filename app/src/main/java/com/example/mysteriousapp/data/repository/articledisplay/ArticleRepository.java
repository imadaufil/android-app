package com.example.mysteriousapp.data.repository.articledisplay;

import android.util.Log;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.api.model.ArticleSearchResponse;
import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.data.repository.articledisplay.local.ArticleLocalDataSource;
import com.example.mysteriousapp.data.repository.articledisplay.remote.ArticleRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class ArticleRepository {

    private ArticleLocalDataSource articleLocalDataSource;
    private ArticleRemoteDataSource articleRemoteDataSource;

    public ArticleRepository(ArticleLocalDataSource articleLocalDataSource, ArticleRemoteDataSource articleRemoteDataSource) {
        this.articleLocalDataSource = articleLocalDataSource;
        this.articleRemoteDataSource = articleRemoteDataSource;
    }

    public Single<ArticleSearchResponse> getArticles(){
        return this.articleRemoteDataSource.getArticles()
                .zipWith(articleLocalDataSource.getSavedForLaterList(), new BiFunction<ArticleSearchResponse, List<String>, ArticleSearchResponse>() {
                    @Override
                    public ArticleSearchResponse apply(ArticleSearchResponse articleSearchResponse, List<String> titleList) throws Exception {
                        for (Article article : articleSearchResponse.getArticles()) {
                            if (titleList.contains(article.getTitle())) {
                                article.savedForLater();
                            }
                        }
                        return articleSearchResponse;
                    }
                });
    }

    public Completable addArticleToSavedForLater(ArticleEntity articleEntity) {
        return articleLocalDataSource.addArticleForSavedForLater(articleEntity);
    }

    public Completable removeArticleFromSavedForLater(String articleId) {
        return articleLocalDataSource.removeArticleFromSavedForLater(articleId);
    }

    public Flowable<List<ArticleEntity>> getSavedForLater() {
        return articleLocalDataSource.getSavedForLater();
    }
}
