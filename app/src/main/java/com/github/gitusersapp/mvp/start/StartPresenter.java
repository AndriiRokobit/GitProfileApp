package com.github.gitusersapp.mvp.start;

import android.content.Context;
import android.widget.Toast;

import com.github.gitusersapp.business.StartInteractor;
import com.github.gitusersapp.di.scopes.Start;
import com.github.gitusersapp.model.responce.Users;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

import static com.github.gitusersapp.data.constants.Params.PAGE;


/**
 * /**
 * Created by Andriy Lykhtey on 2019-07-23.
 */
@Start
@InjectViewState
public class StartPresenter extends MvpPresenter<StartView> {

    private StartInteractor startInteractor;
    private Context context;

    @Inject
    public StartPresenter(StartInteractor startInteractor, Context context) {
        this.startInteractor = startInteractor;
        this.context = context;
    }

    public void searchUsers() {
        startInteractor.allUser(PaginationCons.PAGE, PaginationCons.PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::getUserSuccess, this::getUserFailure);
        }

    private void getUserFailure(Throwable throwable) {
        Toast.makeText(context,"Error get users", Toast.LENGTH_SHORT).show();
    }

    private void getUserSuccess(List<Users> users) {
        getViewState().usersList(users);
    }

    private static class PaginationCons{
        static final int PAGE = 1;
        static final int PER_PAGE = 100;
    }
}
