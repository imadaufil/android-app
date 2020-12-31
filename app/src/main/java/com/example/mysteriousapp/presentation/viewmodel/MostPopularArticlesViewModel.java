package com.example.mysteriousapp.presentation.viewmodel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;
import com.example.mysteriousapp.data.repository.MostPopularArticlesRepository;

public class MostPopularArticlesViewModel extends ViewModel {

    private MostPopularArticlesRepository mostPopularArticlesRepository;

    public MostPopularArticlesViewModel() {
        mostPopularArticlesRepository = new MostPopularArticlesRepository();
    }

    public LiveData<ArticlesHomeResponse> getMostPopularArticles() {
        return mostPopularArticlesRepository.getMostPopularArticles();
    }
}
