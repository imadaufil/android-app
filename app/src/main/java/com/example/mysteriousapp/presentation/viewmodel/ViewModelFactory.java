package com.example.mysteriousapp.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mysteriousapp.data.repository.articledisplay.ArticleRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ArticleRepository articleRepository;

    public ViewModelFactory(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticleViewModel.class)) {
            return (T) new ArticleViewModel(articleRepository);
        }
//        if (modelClass.isAssignableFrom(SavedForLaterViewModel.class)) {
//            return (T) new SavedForLaterViewModel(articleRepository);
//        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}