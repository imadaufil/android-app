package com.example.mysteriousapp.data.api;

import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("home.json")
    Call<ArticlesHomeResponse> getMostPopularArticles(@Query("api-key") String apiKey);
}
