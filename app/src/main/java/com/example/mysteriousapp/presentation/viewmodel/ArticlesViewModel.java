package com.example.mysteriousapp.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysteriousapp.data.repository.ArticleRepository;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.article.articles.mapper.ArticleToViewModelMapper;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public abstract class ArticlesViewModel extends ViewModel {

    private ArticleRepository articleRepository;
    private CompositeDisposable compositeDisposable;
    private ArticleToViewModelMapper articleToViewModelMapper;
    private MutableLiveData<List<ArticleViewItem>> articles = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public ArticlesViewModel(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.articleToViewModelMapper = new ArticleToViewModelMapper();
        getArticles();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public ArticleToViewModelMapper getArticleToViewModelMapper() {
        return articleToViewModelMapper;
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }



    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<ArticleViewItem>> getHomeArticles() {
        return articles;
    }

    public abstract void getArticles();

    public void removeArticleFromSavedForLater(String id) {
        List<ArticleViewItem> articlesTmp = articles.getValue();
        for(ArticleViewItem articleViewItem: articlesTmp) {
            if(articleViewItem.getId().equals(id))
                articleViewItem.setSavedForLater(false);
        }
        articles.setValue(articlesTmp);
    }





}
