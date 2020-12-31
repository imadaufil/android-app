package com.example.mysteriousapp.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.entity.ArticleEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ArticleDao {

    @Query("SELECT * from articleentity")
    Flowable<List<ArticleEntity>> loadSavedForLater();

    @Insert
    public Completable addArticleToSavedForLater(ArticleEntity articleEntity);

    @Query("DELETE FROM articleentity WHERE ID = :id")
    public Completable deleteArticleToSavedForLater(String id);

    @Query("SELECT id from articleentity")
    Single<List<String>> getSavedForLaterList();
}
