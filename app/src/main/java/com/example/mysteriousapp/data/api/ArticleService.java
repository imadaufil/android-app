package com.example.mysteriousapp.data.api;

import com.example.mysteriousapp.data.api.model.ArticleSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("https://api.nytimes.com/svc/topstories/v2/home.json")
    Single<ArticleSearchResponse> getHome(@Query("api-key") String api_key);
}
