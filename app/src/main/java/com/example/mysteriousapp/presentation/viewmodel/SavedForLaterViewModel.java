package com.example.mysteriousapp.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.data.repository.ArticleRepository;
import com.example.mysteriousapp.data.repository.mapper.ArticleToArticleEntityMapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class SavedForLaterViewModel extends ViewModel {
    private ArticleRepository articleRepository;
    private CompositeDisposable compositeDisposable;
    private ArticleToArticleEntityMapper articleToArticleEntityMapper;

    final MutableLiveData<Event<ArticleEntity>> articleAddedEvent = new MutableLiveData<Event<ArticleEntity>>();
    final MutableLiveData<Event<String>> articleDeletedEvent = new MutableLiveData<Event<String>>();

    public SavedForLaterViewModel(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        compositeDisposable = new CompositeDisposable();
        articleToArticleEntityMapper = new ArticleToArticleEntityMapper();
    }

    public void addArticleToSavedForLater(final ArticleEntity articleEntity) {
        compositeDisposable.add(articleRepository.addArticleToSavedToLater(articleEntity)
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        articleAddedEvent.setValue(new Event<ArticleEntity>(articleEntity));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    public void deleteArticleFromSavedForLater(final String id) {
        compositeDisposable.add(articleRepository.deleteArticleFromSavedForLater(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    articleDeletedEvent.setValue(new Event<String>(id));
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            }));
    }

}
