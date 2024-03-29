package com.github.gitusersapp.repository;

import com.github.gitusersapp.data.api.BaseApi;
import com.github.gitusersapp.di.scopes.Start;
import com.github.gitusersapp.model.responce.User;
import com.github.gitusersapp.model.responce.Users;
import com.github.gitusersapp.model.responce.UsersList;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;


@Start
public class StartRepository {

    private final BaseApi baseApi;

    @Inject
    public StartRepository(BaseApi baseApi) {
        this.baseApi = baseApi;
    }

    public Single<List<Users>> allUser(int page, int perPage){
        return baseApi.getUsers(page, perPage);
    }

    public Single<User> getUser(String userName){
        return baseApi.getUser(userName);
    }

    public Observable<UsersList> searchUsers(final String searchTerm) {
        return baseApi.searchGitUsers(searchTerm);

    }
}
