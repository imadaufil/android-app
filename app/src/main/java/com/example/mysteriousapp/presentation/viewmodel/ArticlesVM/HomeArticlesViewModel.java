package com.example.mysteriousapp.presentation.viewmodel.ArticlesVM;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mysteriousapp.ArticleApplication;
import com.example.mysteriousapp.data.api.model.ArticlesResponse;
import com.example.mysteriousapp.data.repository.ArticleRepository;
import com.example.mysteriousapp.presentation.article.articles.adapter.ArticleViewItem;
import com.example.mysteriousapp.presentation.article.articles.mapper.ArticleToViewModelMapper;
import com.example.mysteriousapp.presentation.viewmodel.ArticlesViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeArticlesViewModel extends ArticlesViewModel {

    private ArticleRepository articleRepository;
    private CompositeDisposable compositeDisposable;
    private ArticleToViewModelMapper articleToViewModelMapper;
    private MutableLiveData<List<ArticleViewItem>> articles;

    public HomeArticlesViewModel(ArticleRepository articleRepository) {
        super(articleRepository);
    }

    public void getArticles() {
        compositeDisposable = super.getCompositeDisposable();
        articleToViewModelMapper = super.getArticleToViewModelMapper();
        articleRepository = super.getArticleRepository();
        articles = super.getHomeArticles();
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

}
