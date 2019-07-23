package com.github.gitusersapp.mvp.userdetail;

import com.github.gitusersapp.model.responce.User;
import com.github.gitusersapp.mvp.base.BaseMvpView;

import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

/**
 * /**
 * Created by Andriy Lykhtey on 2019-07-23.
 */
public interface UserDetailView extends BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void userInfo(User user);
}
