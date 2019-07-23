package com.github.gitusersapp.di.app;

import com.github.gitusersapp.di.start.StartComponent;
import com.github.gitusersapp.mvp.start.StartActivity;
import com.github.gitusersapp.mvp.userdetail.UserDetailFragment;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppContextModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    StartComponent plusStartComponent();

    void inject(StartActivity startActivity);

    void inject(UserDetailFragment userDetailFragment);
}
