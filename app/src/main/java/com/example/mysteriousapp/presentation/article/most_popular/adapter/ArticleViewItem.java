package com.example.mysteriousapp.presentation.article.most_popular.adapter;

public class ArticleViewItem {
    private String title;
    private String thumbnailUrl;
    private String summary;

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
}
