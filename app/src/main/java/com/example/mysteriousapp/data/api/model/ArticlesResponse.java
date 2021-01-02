package com.example.mysteriousapp.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponse {

    @SerializedName("section")
    private String section;

    @SerializedName("num_results")
    private int num_results;

    @SerializedName("results")
    private List<Article> results;

    public String getSection() {
        return section;
    }

    public int getNum_results() {
        return num_results;
    }

    public List<Article> getResults() {
        return results;
    }
}
