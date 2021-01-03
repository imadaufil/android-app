package com.example.mysteriousapp.data.repository.local;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.db.ArticleDataBase;
import com.example.mysteriousapp.data.entity.ArticleEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class ArticleLocalDataSource {

    private ArticleDataBase articleDataBase;

    public ArticleLocalDataSource(ArticleDataBase articleDataBase) {
        this.articleDataBase = articleDataBase;
    }

    public Flowable<List<ArticleEntity>> loadSavedForLater() {
        return articleDataBase.articleDao().loadSavedForLater();
    }

    public Completable addArticleToSavedForLater(ArticleEntity articleEntity) {
        return articleDataBase.articleDao().addArticleToSavedForLater(articleEntity);
    }

    public Completable deleteArticleToSavedForLater(String id) {
        return articleDataBase.articleDao().deleteArticleToSavedForLater(id);
    }

    public Single<List<String>> getSavedForLaterList() {
        return articleDataBase.articleDao().getSavedForLaterList();
    }
}
