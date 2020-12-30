package com.example.mysteriousapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysteriousapp.data.api.model.ArticleSearchResponse;
import com.example.mysteriousapp.data.repository.articledisplay.ArticleRepository;
import com.example.mysteriousapp.presentation.articledisplay.mostpopular.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.articledisplay.mostpopular.mapper.ArticleToViewModelMapper;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ArticleViewModel extends ViewModel {
    private ArticleRepository articleRepository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    private MutableLiveData<Boolean> errorConnexion = new MutableLiveData<Boolean>();
    private ArticleToViewModelMapper articleToViewModelMapper;
    private MutableLiveData<List<ArticleViewItem>> articles = new MutableLiveData<List<ArticleViewItem>>();

    public ArticleViewModel(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        this.articleToViewModelMapper = new ArticleToViewModelMapper();
        this.compositeDisposable = new CompositeDisposable();
    }

    public void getHomeArticles() {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(articleRepository.getArticles()
        .subscribeOn(Schedulers.io())
        .subscribeWith(new DisposableSingleObserver<ArticleSearchResponse>() {

            @Override
            public void onSuccess(@NonNull ArticleSearchResponse articleSearchResponse) {
                articles.setValue(articleToViewModelMapper.map(articleSearchResponse.getArticles()));
                isDataLoading.setValue(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                errorConnexion.setValue(true);
                isDataLoading.setValue(false);
            }
        }));
    }

    public LiveData<List<ArticleViewItem>> getArticles() {return this.articles; }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<Boolean> getErrorConnexion() {
        return errorConnexion;
    }
}
