package com.github.gitusersapp.di.start;

import com.github.gitusersapp.di.scopes.Start;
import com.github.gitusersapp.mvp.start.adapter.UsersAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class StartModule {

    @Start
    @Provides
    public static UsersAdapter provideUsersAdapter() {
        return new UsersAdapter();
    }
}
