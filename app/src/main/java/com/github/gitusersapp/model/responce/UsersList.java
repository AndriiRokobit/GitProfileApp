package com.github.gitusersapp.model.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersList {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<User> users;

    //for moc service in future
    public UsersList(final List<User> githubUsers) {
        this.users = githubUsers;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public List<User> getItems() {
        return users;
    }


}
