package com.example.mysteriousapp.data.di;

import android.content.Context;
import android.view.View;

import androidx.room.Room;

import com.example.mysteriousapp.data.api.ApiService;
import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.db.ArticleDataBase;
import com.example.mysteriousapp.data.repository.ArticleRepository;
import com.example.mysteriousapp.data.repository.local.ArticleLocalDataSource;
import com.example.mysteriousapp.data.repository.mapper.ArticleToArticleEntityMapper;
import com.example.mysteriousapp.data.repository.remote.ArticleRemoteDataSource;
import com.example.mysteriousapp.presentation.viewmodel.ViewModelFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {

    private static ArticleDataBase articleDataBase;
    public static ArticleRepository articleRepository;
    public static ApiService apiService;
    public static Retrofit retrofit;
    private static Gson gson;
    private static ViewModelFactory viewModelFactory;
    private static Context applicationContext;


    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null)
            viewModelFactory = new ViewModelFactory(getArticleRepository());
        return viewModelFactory;
    }



    public static ArticleRepository getArticleRepository() {
        if (articleRepository == null)
            articleRepository = new ArticleRepository(new ArticleRemoteDataSource(getApiService()), new ArticleLocalDataSource(getArticleDataBase()), new ArticleToArticleEntityMapper());
        return articleRepository;
    }

    public static ApiService getApiService() {
        if (apiService == null)
            apiService = getRetrofit().create(ApiService.class);
        return apiService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setApplicationContext(Context context) {
        applicationContext = context;
    }

    public static ArticleDataBase getArticleDataBase() {
        if (articleDataBase == null)
            articleDataBase = Room.databaseBuilder(applicationContext,
                    ArticleDataBase.class, "article-database").build();
        return articleDataBase;
    }
}
