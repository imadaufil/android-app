package com.example.mysteriousapp.data.api;

import com.example.mysteriousapp.data.api.model.ArticlesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("home.json")
    Single<ArticlesResponse> getHomeArticles(@Query("api-key") String apiKey);

    @GET("arts.json")
    Single<ArticlesResponse> getArtsArticles(@Query("api-key") String apiKey);

    @GET("automobiles.json")
    Single<ArticlesResponse> getAutomobilesArticles(@Query("api-key") String apiKey);

    @GET("books.json")
    Single<ArticlesResponse> getBooksArticles(@Query("api-key") String apiKey);

    @GET("business.json")
    Single<ArticlesResponse> getBusinessArticles(@Query("api-key") String apiKey);

    @GET("fashion.json")
    Single<ArticlesResponse> getFashionArticles(@Query("api-key") String apiKey);

    @GET("food.json")
    Single<ArticlesResponse> getFoodArticles(@Query("api-key") String apiKey);

    @GET("health.json")
    Single<ArticlesResponse> getHealthArticles(@Query("api-key") String apiKey);

    @GET("insider.json")
    Single<ArticlesResponse> getInsiderArticles(@Query("api-key") String apiKey);

    @GET("magazine.json")
    Single<ArticlesResponse> getMagazineArticles(@Query("api-key") String apiKey);

    @GET("movies.json")
    Single<ArticlesResponse> getMoviesArticles(@Query("api-key") String apiKey);

    @GET("nyregion.json")
    Single<ArticlesResponse> getNYRegionArticles(@Query("api-key") String apiKey);

    @GET("obituaries.json")
    Single<ArticlesResponse> getObituariesArticles(@Query("api-key") String apiKey);

    @GET("opinion.json")
    Single<ArticlesResponse> getOpinionArticles(@Query("api-key") String apiKey);

    @GET("politics.json")
    Single<ArticlesResponse> getPoliticsArticles(@Query("api-key") String apiKey);

    @GET("realestate.json")
    Single<ArticlesResponse> getRealEstateArticles(@Query("api-key") String apiKey);

    @GET("science.json")
    Single<ArticlesResponse> getScienceArticles(@Query("api-key") String apiKey);

    @GET("sports.json")
    Single<ArticlesResponse> getSportsArticles(@Query("api-key") String apiKey);

    @GET("sundayreview.json")
    Single<ArticlesResponse> getSundayReviewArticles(@Query("api-key") String apiKey);

    @GET("technology.json")
    Single<ArticlesResponse> getTechnologyArticles(@Query("api-key") String apiKey);

    @GET("theater.json")
    Single<ArticlesResponse> getTheaterArticles(@Query("api-key") String apiKey);

    @GET("t-magazine.json")
    Single<ArticlesResponse> getTMagazineArticles(@Query("api-key") String apiKey);

    @GET("travel.json")
    Single<ArticlesResponse> getTravelArticles(@Query("api-key") String apiKey);

    @GET("upshot.json")
    Single<ArticlesResponse> getUPShotArticles(@Query("api-key") String apiKey);

    @GET("us.json")
    Single<ArticlesResponse> getUSArticles(@Query("api-key") String apiKey);

    @GET("world.json")
    Single<ArticlesResponse> getWorldArticles(@Query("api-key") String apiKey);

}


/*
arts, automobiles, books, business, fashion, food,
health, home, insider, magazine, movies, nyregion, obituaries,
opinion, politics, realestate, science, sports, sundayreview, technology,
theater, t-magazine, travel, upshot, us, world
 */