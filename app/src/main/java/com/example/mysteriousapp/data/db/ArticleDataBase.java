package com.example.mysteriousapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mysteriousapp.data.entity.ArticleEntity;

@TypeConverters(Converters.class)
@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class ArticleDataBase extends RoomDatabase {
    public abstract ArticleDao articleDao();

}
