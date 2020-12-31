package com.example.mysteriousapp.data.repository;

import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mysteriousapp.ArticleApplication;
import com.example.mysteriousapp.data.api.ApiClient;
import com.example.mysteriousapp.data.api.ApiService;
import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;
import com.example.mysteriousapp.data.repository.local.ArticleLocalDataSource;
import com.example.mysteriousapp.data.repository.remote.ArticleRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private ArticleRemoteDataSource articleRemoteDataSource;
    private ArticleLocalDataSource articleLocalDataSource;

    public ArticleRepository(ArticleRemoteDataSource articleRemoteDataSource, ArticleLocalDataSource articleLocalDataSource) {
        this.articleRemoteDataSource = articleRemoteDataSource;
        this.articleLocalDataSource = articleLocalDataSource;
    }

    public Single<ArticlesHomeResponse> getMostPopularArticles(String apiKey) {
        return articleRemoteDataSource.getMostPopularArticles(apiKey);
    }
}
