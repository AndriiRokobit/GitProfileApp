package com.github.gitusersapp.di.app;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {

    private static Context appContext;

    public AppContextModule(@NonNull Context context) {
        appContext = context;
    }

    @Provides
    @Singleton
    public static Context provideContext() {
        return appContext;
    }
}
