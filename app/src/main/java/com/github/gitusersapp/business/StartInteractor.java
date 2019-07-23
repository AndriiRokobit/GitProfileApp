package com.github.gitusersapp.business;

import com.github.gitusersapp.di.scopes.Start;
import com.github.gitusersapp.model.responce.User;
import com.github.gitusersapp.model.responce.Users;
import com.github.gitusersapp.model.responce.UsersList;
import com.github.gitusersapp.repository.StartRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

@Start
public class StartInteractor {
    private final StartRepository startRepository;

    @Inject
    public StartInteractor(StartRepository startRepository) {
        this.startRepository = startRepository;
    }

    public Single<User> getUser(String userName) {
        return startRepository.getUser(userName);
    }

    public Single<List<Users>> allUser(int page, int perPage) {
        return startRepository.allUser(page, perPage);
    }

    public Observable<UsersList> searchUsers(String term) {
        return startRepository.searchUsers(term);
    }
}
