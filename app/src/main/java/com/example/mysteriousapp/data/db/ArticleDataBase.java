package com.example.mysteriousapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mysteriousapp.data.entity.ArticleEntity;

@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class ArticleDataBase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
