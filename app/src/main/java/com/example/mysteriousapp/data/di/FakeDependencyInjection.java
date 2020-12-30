package com.example.mysteriousapp.data.di;

import android.content.Context;
import androidx.room.Room;
import com.example.mysteriousapp.data.api.ArticleService;
import com.example.mysteriousapp.data.api.model.Article;
import com.example.mysteriousapp.data.db.ArticleDataBase;
import com.example.mysteriousapp.data.repository.articledisplay.ArticleRepository;
import com.example.mysteriousapp.data.repository.articledisplay.local.ArticleLocalDataSource;
import com.example.mysteriousapp.data.repository.articledisplay.mapper.ArticleToArticleEntityMapper;
import com.example.mysteriousapp.data.repository.articledisplay.remote.ArticleRemoteDataSource;
import com.example.mysteriousapp.presentation.viewmodel.ViewModelFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {

    private static ArticleService articleService;
    private static ArticleRepository articleRepository;
    private static ArticleDataBase articleDatabase;
    private static Retrofit retrofit;
    private static Gson gson;
    private static Context applicationContext;
    private static ViewModelFactory viewModelFactory;


    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getArticleRepository());
        }
        return viewModelFactory;
    }

    public static ArticleRepository getArticleRepository() {
        if (articleRepository == null) {
            articleRepository = new ArticleRepository(
                    new ArticleLocalDataSource(getArticleDataBase()),
                    new ArticleRemoteDataSource(getArticleService())
            );
        }
        return articleRepository;
    }



    public static ArticleService getArticleService() {
        if (articleService == null) {
            articleService = getRetrofit().create(ArticleService.class);
        }
        return articleService;
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

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static ArticleDataBase getArticleDataBase() {
        if (articleDatabase == null) {
            articleDatabase = Room.databaseBuilder(applicationContext,
                    ArticleDataBase.class, "article-database").build();
        }
        return articleDatabase;
    }


}
