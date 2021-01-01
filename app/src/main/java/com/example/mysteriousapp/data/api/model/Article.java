package com.example.mysteriousapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {

    @SerializedName("uri")
    private String id;


    @SerializedName("title")
    private String title;


    @SerializedName("abstract")
    private String summary;

    @SerializedName("url")
    private String url;

    @SerializedName("byline")
    private String byline;

    @SerializedName("published_date")
    private String published_date;

    @SerializedName("multimedia")
    private List<Multimedia> multimedia;

    private boolean savedForLater;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getUrl() {
        return url;
    }

    public String getByline() {
        return byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public boolean isSavedForLater() {
        return savedForLater;
    }

    public void setSavedForLater() {
        savedForLater = true;
    }
}
