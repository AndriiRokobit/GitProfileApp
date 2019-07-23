package com.github.gitusersapp.data.api;

import com.github.gitusersapp.data.constants.Endpoints;
import com.github.gitusersapp.data.constants.Params;
import com.github.gitusersapp.model.responce.User;
import com.github.gitusersapp.model.responce.Users;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApi {

    @GET(Endpoints.USER)
    Single<User> getUser(@Path(Params.USER) String username);

    @GET(Endpoints.USERS_SINCE)
    Single<List<Users>> getUsers(@Query(Params.PAGE) int page, @Query(Params.PER_PAGE) int perPage);
}
