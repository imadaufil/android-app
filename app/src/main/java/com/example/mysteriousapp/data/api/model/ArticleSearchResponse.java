package com.example.mysteriousapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleSearchResponse {
    @SerializedName("results")
    List<Article> articles;

    int num_results;

    public List<Article> getArticles() {
        return articles;
    }

    public int getNum_results() {
        return num_results;
    }
}
