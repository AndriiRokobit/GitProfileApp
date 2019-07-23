package com.github.gitusersapp.di.start;

import com.github.gitusersapp.di.scopes.Start;
import com.github.gitusersapp.mvp.start.StartPresenter;
import com.github.gitusersapp.mvp.userdetail.UserDetailPresenter;

import dagger.Subcomponent;

@Start
@Subcomponent(modules = {StartModule.class})
public interface StartComponent {

    StartPresenter provideStartPresenter();

    UserDetailPresenter provideUserDetailPresenter();
}
