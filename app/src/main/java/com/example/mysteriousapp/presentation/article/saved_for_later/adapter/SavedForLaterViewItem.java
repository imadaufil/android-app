package com.example.mysteriousapp.presentation.article.saved_for_later.adapter;

public class SavedForLaterViewItem {
    private String title;
    private String thumbnailUrl;
    private String summary;
    private boolean savedForLater;
    private String id;
    private String url;

    private String byline;
    private String published_date;
    private String caption;
    private String copyright;
    private String format;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String getThumbnailUrl) {
        this.thumbnailUrl = getThumbnailUrl;
    }

    public String getAbstract() {
        return summary;
    }

    public void setAbstract(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isSavedForLater() {
        return savedForLater;
    }

    public void setSavedForLater(boolean savedForLater) {
        this.savedForLater = savedForLater;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
