package com.example.mysteriousapp.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mysteriousapp.data.repository.ArticleRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ArticleRepository articleRepository;

    public ViewModelFactory(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticlesViewModel.class))
            return (T) new ArticlesViewModel(articleRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
