package com.example.mysteriousapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.repository.ArticleRepository;

import java.util.List;

public class ArticlesViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articles;
    private ArticleRepository articleRepository;

    /*
    public void init() {
        if (articles != null) return;
        articleRepository = ArticleRepository.getInstance();
        articles = articleRepository.getAllArticles();
    }

    public LiveData<List<Article>> getArticles() {
        return articles;
    }

     */



}
