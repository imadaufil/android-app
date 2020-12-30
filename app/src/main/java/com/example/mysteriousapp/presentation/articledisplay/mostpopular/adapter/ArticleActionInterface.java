package com.example.mysteriousapp.presentation.articledisplay.mostpopular.adapter;

public interface ArticleActionInterface {

    void onInfoClicked(String articleTitle, String urlImage);

    void onFav(String articleTitle, String urlImage);
}
