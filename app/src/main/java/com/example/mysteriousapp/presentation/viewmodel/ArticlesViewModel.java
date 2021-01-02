package com.example.mysteriousapp.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysteriousapp.ArticleApplication;
import com.example.mysteriousapp.data.api.model.ArticlesResponse;
import com.example.mysteriousapp.data.repository.ArticleRepository;
import com.example.mysteriousapp.presentation.article.most_popular.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.article.most_popular.mapper.ArticleToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ArticlesViewModel extends ViewModel {

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



    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<ArticleViewItem>> getMostPopularArticles() {
        return articles;
    }

    public void getArticles() {
        compositeDisposable.clear();
        compositeDisposable.add(articleRepository.getHomeArticles(ArticleApplication.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<ArticlesResponse>() {
                @Override
                public void onSuccess(@NonNull ArticlesResponse articlesResponse) {
                    articles.setValue(articleToViewModelMapper.map(articlesResponse.getResults()));
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d("onError", "call onError");
                }
            }));
    }

    public void removeArticleFromSavedForLater(String id) {
        List<ArticleViewItem> articlesTmp = articles.getValue();
        for(ArticleViewItem articleViewItem: articlesTmp) {
            if(articleViewItem.getId().equals(id))
                articleViewItem.setSavedForLater(false);
        }
        articles.setValue(articlesTmp);
    }



}
