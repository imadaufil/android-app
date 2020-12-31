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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private static ArticleRepository instance;
    private ArticleLocalDataSource articleLocalDataSource = new ArticleLocalDataSource();
    private ArrayList<Article> data = new ArrayList<>();

    public static ArticleRepository getInstance() {
        if(instance == null) instance = new ArticleRepository();
        return instance;
    }

    /*
    public MutableLiveData<List<Article>> getAllArticles() {
        data.addAll(articleLocalDataSource.setArticlesLocaly());
        MutableLiveData<List<Article>> res = new MutableLiveData<>();
        res.setValue(data);
        return res;
    }

     */
}
