package com.example.mysteriousapp.data.repository.remote;

import com.example.mysteriousapp.data.api.ApiService;
import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;

import io.reactivex.Single;

public class ArticleRemoteDataSource {
    private ApiService apiService;

    public ArticleRemoteDataSource(ApiService apiService){
        this.apiService = apiService;
    }

    public Single<ArticlesHomeResponse> getMostPopularArticles(String apiKey) {
        return apiService.getMostPopularArticles(apiKey);
    }
}
