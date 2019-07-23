package com.github.gitusersapp.mvp.base;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface BaseMvpView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void toggleLoading(boolean visible, int messageId);
}
