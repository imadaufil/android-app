package com.example.mysteriousapp.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mysteriousapp.data.repository.ArticleRepository;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesVM.HomeArticlesViewModel;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesVM.TechnologyArticlesViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ArticleRepository articleRepository;

    public ViewModelFactory(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeArticlesViewModel.class))
            return (T) new HomeArticlesViewModel(articleRepository);
        if (modelClass.isAssignableFrom(TechnologyArticlesViewModel.class))
            return (T) new TechnologyArticlesViewModel(articleRepository);
        if (modelClass.isAssignableFrom(SavedForLaterViewModel.class))
            return (T) new SavedForLaterViewModel(articleRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
