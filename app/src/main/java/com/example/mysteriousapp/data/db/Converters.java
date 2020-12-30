package com.example.mysteriousapp.data.db;

import androidx.room.TypeConverter;

import com.example.mysteriousapp.data.api.model.Media;
import com.example.mysteriousapp.data.api.model.Multimedia;

import java.util.ArrayList;
import java.util.List;

public class Converters {

    @TypeConverter
    public String fromMultimedia(List<Media> multimedia) {
        return multimedia.get(0).getUrl();
    }

    @TypeConverter
    public List<Media> toMultimedia(String url){
        return new ArrayList<Media>();
    }
}
