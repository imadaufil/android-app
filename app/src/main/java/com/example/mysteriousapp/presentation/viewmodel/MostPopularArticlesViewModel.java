package com.example.mysteriousapp.presentation.viewmodel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;
import com.example.mysteriousapp.data.repository.ArticleRepository;
import com.example.mysteriousapp.data.repository.MostPopularArticlesRepository;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.article.most_popular.mapper.ArticleToViewModelMapper;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MostPopularArticlesViewModel extends ViewModel {

    private ArticleRepository articleRepository;
    private CompositeDisposable compositeDisposable;
    private ArticleToViewModelMapper articleToViewModelMapper;
    private MutableLiveData<List<ArticleViewItem>> articles = new MutableLiveData<>();

    /*
    public MostPopularArticlesViewModel() {
        mostPopularArticlesRepository = new MostPopularArticlesRepository();
    }

    public LiveData<ArticlesHomeResponse> getMostPopularArticles() {
        return mostPopularArticlesRepository.getMostPopularArticles();
    }

     */
}
