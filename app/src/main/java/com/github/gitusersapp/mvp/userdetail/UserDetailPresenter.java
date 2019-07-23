package com.github.gitusersapp.mvp.userdetail;

import android.content.Context;
import android.widget.Toast;

import com.github.gitusersapp.business.StartInteractor;
import com.github.gitusersapp.di.scopes.Start;
import com.github.gitusersapp.model.responce.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

/**
 * /**
 * Created by Andriy Lykhtey on 2019-07-23.
 */
@InjectViewState
@Start
public class UserDetailPresenter extends MvpPresenter<UserDetailView> {

    private StartInteractor startInteractor;
    private Context context;

    @Inject
    public UserDetailPresenter(StartInteractor startInteractor, Context context) {
        this.startInteractor = startInteractor;
        this.context = context;
    }

    public void getUser(String userName) {
        startInteractor.getUser(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getUserSuccess, this::getUserFailure);
    }

    private void getUserSuccess(User user) {
        getViewState().userInfo(user);
    }

    private void getUserFailure(Throwable throwable) {
        Toast.makeText(context, "Error get users detail", Toast.LENGTH_SHORT).show();
    }


}
