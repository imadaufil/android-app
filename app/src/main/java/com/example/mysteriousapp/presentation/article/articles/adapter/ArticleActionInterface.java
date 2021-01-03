package com.example.mysteriousapp.presentation.article.articles.adapter;

public interface ArticleActionInterface {
    void onArticle(String articleTitle, String articleAbstract, String articleThumbnail, String articleCaption, String articleCopyright, String articleByline, String articleURL);

    void onSavedForLaterToggle(String id, String title, String summary, String url,
                               String byline, String published_date, String thumbnailUrl, String caption,
                               String copyright, String format, boolean savedForLater);
}
