package com.github.gitusersapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.github.gitusersapp.di.app.AppComponent;
import com.github.gitusersapp.di.app.AppContextModule;
import com.github.gitusersapp.di.app.DaggerAppComponent;
import com.github.gitusersapp.di.start.StartComponent;

/**
 * /**
 * Created by Andriy Lykhtey on 2019-07-23.
 */
public class AndroidApplication extends Application {
    @NonNull
    private static AppComponent appComponent;

    private static StartComponent startComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initializeDI();
    }

    private void initializeDI() {
        appComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();
    }

    @NonNull
    public static AppComponent applicationComponent() {
        return appComponent;
    }

    @NonNull
    public static StartComponent startComponent() {
        if (startComponent == null) {
            startComponent = appComponent.plusStartComponent();
        }
        return startComponent;
    }
}
