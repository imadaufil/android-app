package com.example.mysteriousapp.data.db;

import androidx.room.TypeConverter;

import com.example.mysteriousapp.data.api.model.Multimedia;

public class Converters {

    @TypeConverter
    public String fromMultimedia(Multimedia multimedia) {
        return multimedia.getMedia().get(0).getUrl();
    }

    @TypeConverter
    public Multimedia toMultimedia(String url){
        return new Multimedia();
    }
}
