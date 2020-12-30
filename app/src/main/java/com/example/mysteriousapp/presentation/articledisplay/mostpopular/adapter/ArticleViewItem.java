package com.example.mysteriousapp.presentation.articledisplay.mostpopular.adapter;

public class ArticleViewItem {
    private String title;
    private String urlImage;
    private boolean savedForLater;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public boolean isSavedForLater() {
        return savedForLater;
    }

    public void setSavedForLater(boolean savedForLater) {
        this.savedForLater = savedForLater;
    }
}
