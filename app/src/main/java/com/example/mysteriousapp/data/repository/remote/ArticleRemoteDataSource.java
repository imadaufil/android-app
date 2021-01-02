package com.example.mysteriousapp.data.repository.remote;

import com.example.mysteriousapp.data.api.ApiService;
import com.example.mysteriousapp.data.api.model.ArticlesResponse;

import io.reactivex.Single;

public class ArticleRemoteDataSource {
    private ApiService apiService;

    public ArticleRemoteDataSource(ApiService apiService){
        this.apiService = apiService;
    }

    public Single<ArticlesResponse> getHomeArticles(String apiKey) {
        return apiService.getHomeArticles(apiKey);
    }

    public Single<ArticlesResponse> getArtsArticles(String apiKey) {
        return apiService.getArtsArticles(apiKey);
    }

    public Single<ArticlesResponse> getAutomobilesArticles(String apiKey) {
        return apiService.getAutomobilesArticles(apiKey);
    }

    public Single<ArticlesResponse> getBooksArticles(String apiKey) {
        return apiService.getBooksArticles(apiKey);
    }

    public Single<ArticlesResponse> getBusinessArticles(String apiKey) {
        return apiService.getBusinessArticles(apiKey);
    }

    public Single<ArticlesResponse> getFashionArticles(String apiKey) {
        return apiService.getFashionArticles(apiKey);
    }

    public Single<ArticlesResponse> getFoodArticles(String apiKey) {
        return apiService.getFoodArticles(apiKey);
    }

    public Single<ArticlesResponse> getHealthArticles(String apiKey) {
        return apiService.getHealthArticles(apiKey);
    }

    public Single<ArticlesResponse> getInsiderArticles(String apiKey) {
        return apiService.getInsiderArticles(apiKey);
    }

    public Single<ArticlesResponse> getMagazineArticles(String apiKey) {
        return apiService.getMagazineArticles(apiKey);
    }

    public Single<ArticlesResponse> getMoviesArticles(String apiKey) {
        return apiService.getMoviesArticles(apiKey);
    }

    public Single<ArticlesResponse> getNYRegionArticles(String apiKey) {
        return apiService.getNYRegionArticles(apiKey);
    }

    public Single<ArticlesResponse> getObituariesArticles(String apiKey) {
        return apiService.getObituariesArticles(apiKey);
    }

    public Single<ArticlesResponse> getOpinionArticles(String apiKey) {
        return apiService.getOpinionArticles(apiKey);
    }

    public Single<ArticlesResponse> getPoliticsArticles(String apiKey) {
        return apiService.getPoliticsArticles(apiKey);
    }

    public Single<ArticlesResponse> getRealEstateArticles(String apiKey) {
        return apiService.getRealEstateArticles(apiKey);
    }

    public Single<ArticlesResponse> getScienceArticles(String apiKey) {
        return apiService.getScienceArticles(apiKey);
    }

    public Single<ArticlesResponse> getSportsArticles(String apiKey) {
        return apiService.getSportsArticles(apiKey);
    }

    public Single<ArticlesResponse> getSundayReviewArticles(String apiKey) {
        return apiService.getSundayReviewArticles(apiKey);
    }

    public Single<ArticlesResponse> getTechnologyArticles(String apiKey) {
        return apiService.getTechnologyArticles(apiKey);
    }

    public Single<ArticlesResponse> getTheaterArticles(String apiKey) {
        return apiService.getTheaterArticles(apiKey);
    }

    public Single<ArticlesResponse> getTMagazineArticles(String apiKey) {
        return apiService.getTMagazineArticles(apiKey);
    }

    public Single<ArticlesResponse> getTravelArticles(String apiKey) {
        return apiService.getTravelArticles(apiKey);
    }

    public Single<ArticlesResponse> getUPShotArticles(String apiKey) {
        return apiService.getUPShotArticles(apiKey);
    }

    public Single<ArticlesResponse> getUSArticles(String apiKey) {
        return apiService.getUSArticles(apiKey);
    }

    public Single<ArticlesResponse> getWorldArticles(String apiKey) {
        return apiService.getWorldArticles(apiKey);
    }
}
