package com.example.mysteriousapp.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mysteriousapp.ArticleApplication;
import com.example.mysteriousapp.data.entity.ArticleEntity;
import com.example.mysteriousapp.data.repository.ArticleRepository;
import com.example.mysteriousapp.data.repository.mapper.ArticleToArticleEntityMapper;
import com.example.mysteriousapp.presentation.article.saved_for_later.adapter.SavedForLaterViewItem;
import com.example.mysteriousapp.presentation.article.saved_for_later.mapper.ArticleEntityToSavedForLaterViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class SavedForLaterViewModel extends ViewModel {
    private ArticleRepository articleRepository;
    private CompositeDisposable compositeDisposable;
    private ArticleToArticleEntityMapper articleToArticleEntityMapper;
    private ArticleEntityToSavedForLaterViewModelMapper articleEntityToSavedForLaterViewModelMapper;

    final MutableLiveData<Event<String>> articleAddedEvent = new MutableLiveData<Event<String>>();
    final MutableLiveData<Event<String>> articleDeletedEvent = new MutableLiveData<Event<String>>();

    public SavedForLaterViewModel(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        compositeDisposable = new CompositeDisposable();
        articleToArticleEntityMapper = new ArticleToArticleEntityMapper();
        articleEntityToSavedForLaterViewModelMapper = new ArticleEntityToSavedForLaterViewModelMapper();
    }

    public void addArticleToSavedForLater(final ArticleEntity articleEntity) {
        compositeDisposable.add(articleRepository.addArticleToSavedToLater(articleEntity)
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        articleAddedEvent.setValue(new Event<String>(articleEntity.getId()));
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


    private MutableLiveData<List<SavedForLaterViewItem>> savedForLater;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<SavedForLaterViewItem>> getSavedForLater() {
        isDataLoading.setValue(true);
        if (savedForLater == null) {
            savedForLater = new MutableLiveData<List<SavedForLaterViewItem>>();
            compositeDisposable.add(articleRepository.getSavedForLater()
                    .observeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<ArticleEntity>>() {

                        @Override
                        public void onNext(List<ArticleEntity> articleEntities) {
                            isDataLoading.setValue(false);
                            savedForLater.setValue(articleEntityToSavedForLaterViewModelMapper.map(articleEntities));
                        }

                        @Override
                        public void onError(Throwable t) {
                            isDataLoading.setValue(false);
                        }

                        @Override
                        public void onComplete() {
                            isDataLoading.setValue(false);
                        }
                    }));
        }
        return savedForLater;
    }

    public MutableLiveData<Event<String>> getArticleDeletedEvent() {
        return articleAddedEvent;
    }

    public MutableLiveData<Event<String>> getArticleAddedEvent() {
        return articleDeletedEvent;
    }

}
