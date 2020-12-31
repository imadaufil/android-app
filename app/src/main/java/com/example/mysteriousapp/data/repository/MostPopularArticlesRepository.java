package com.example.mysteriousapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mysteriousapp.ArticleApplication;
import com.example.mysteriousapp.data.api.ApiClient;
import com.example.mysteriousapp.data.api.ApiService;
import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularArticlesRepository {

    private ApiService apiService;

    public MostPopularArticlesRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<ArticlesHomeResponse> getMostPopularArticles() {
        final MutableLiveData<ArticlesHomeResponse> data = new MutableLiveData<>();
        apiService.getMostPopularArticles(ArticleApplication.API_KEY).enqueue(new Callback<ArticlesHomeResponse>() {
            @Override
            public void onResponse(Call<ArticlesHomeResponse> call, Response<ArticlesHomeResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticlesHomeResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
