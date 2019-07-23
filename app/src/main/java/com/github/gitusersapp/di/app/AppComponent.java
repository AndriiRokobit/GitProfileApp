package com.github.gitusersapp.di.app;

import com.github.gitusersapp.di.start.StartComponent;
import com.github.gitusersapp.mvp.start.StartActivity;
import com.github.gitusersapp.mvp.userdetail.UserDetailFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * /**
 * Created by Andriy Lykhtey on 2019-07-23.
 */
@Component(modules = {AppContextModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    StartComponent plusStartComponent();

    void inject(StartActivity startActivity);

    void inject(UserDetailFragment userDetailFragment);
}
