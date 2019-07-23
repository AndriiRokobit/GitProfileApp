package com.github.gitusersapp.mvp.start;

import com.github.gitusersapp.model.responce.Users;
import com.github.gitusersapp.mvp.base.BaseMvpView;

import java.util.List;

import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface StartView extends BaseMvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void usersList(List<Users> users);
}
