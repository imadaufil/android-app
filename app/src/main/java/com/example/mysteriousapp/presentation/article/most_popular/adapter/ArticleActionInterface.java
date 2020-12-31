package com.example.mysteriousapp.presentation.article.most_popular.adapter;

public interface ArticleActionInterface {
    void onArticle(String articleTitle, String articleAbstract, String articleThumbnail);

    void onSavedForLaterToggle(String id, String title, String summary, String url,
                               String byline, String published_date, String thumbnailUrl, String caption,
                               String copyright, String format, boolean savedForLater);
}
