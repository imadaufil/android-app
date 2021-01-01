package com.example.mysteriousapp.presentation.article.saved_for_later.adapter;

public interface SavedForLaterActionInterface {
    void onArticle(String articleTitle, String articleAbstract, String articleThumbnail, boolean savedForLater);
    void onRemoveSavedForLater(String id);
}
