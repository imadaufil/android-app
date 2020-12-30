package com.example.mysteriousapp;

import android.app.Application;

import com.example.mysteriousapp.data.di.FakeDependencyInjection;
import com.facebook.stetho.Stetho;

public class StoriesApplication extends Application {

    public static final String API_KEY = "43Ln9CrwqUfGXNohZ2h6Ai9R8iK3q5zs";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }

}
