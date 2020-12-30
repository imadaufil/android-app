package com.example.mysteriousapp.data.repository.articledisplay.local;

import com.example.mysteriousapp.data.db.ArticleDataBase;
import com.example.mysteriousapp.data.entity.ArticleEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class ArticleLocalDataSource {
    private ArticleDataBase articleDataBase;

    public ArticleLocalDataSource(ArticleDataBase articleDataBase) {
        this.articleDataBase = articleDataBase;
    }

    public Flowable<List<ArticleEntity>> getSavedForLater() {
        return articleDataBase.articleDao().getSavedForLaterArticles();
    }

    public Completable addArticleForSavedForLater(ArticleEntity articleEntity) {
        return articleDataBase.articleDao().addArticleToSavedForLater(articleEntity);
    }

    public Completable removeArticleFromSavedForLater(String title) {
        return articleDataBase.articleDao().deleteArticleFromSavedForLater(title);
    }

    public Single<List<String>> getSavedForLaterList() {
        return articleDataBase.articleDao().getSavedForLaterIdList();
    }
}
