package com.example.mysteriousapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {
    private String section;
    private String title;

    @SerializedName("abstract")
    private String summary;

    private String url;
    private String uri;
    private String byline;

    private String published_date;

    private List<Media> multimedia;

    private boolean savedForLater;


    public String getId() {
        return uri;
    }


    public String getSection() {
        return section;
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

    public List<Media> getMultimedia() {
        return multimedia;
    }

    public boolean isSavedForLater() {
        return savedForLater;
    }

    public void savedForLater() {
        this.savedForLater = true;
    }
}
