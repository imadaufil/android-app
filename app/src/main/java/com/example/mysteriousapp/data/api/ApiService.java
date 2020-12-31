package com.example.mysteriousapp.data.api;

import com.example.mysteriousapp.data.api.model.ArticlesHomeResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("home.json")
    Single<ArticlesHomeResponse> getMostPopularArticles(@Query("api-key") String apiKey);
}
