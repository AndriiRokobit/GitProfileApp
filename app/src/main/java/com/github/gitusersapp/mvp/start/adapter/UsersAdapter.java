package com.github.gitusersapp.mvp.start.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.github.gitusersapp.R;
import com.github.gitusersapp.model.responce.User;
import com.github.gitusersapp.model.responce.Users;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * /**
 * Created by Andriy Lykhtey on 2019-07-23.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<Users> users = Collections.emptyList();
    private OnUsersItemClickListener onUsersItemClickListener;

    public void setUserList(List<Users> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void setOnUsersItemClickListener(OnUsersItemClickListener onUsersItemClickListener) {
        this.onUsersItemClickListener = onUsersItemClickListener;
    }

    @Inject
    public UsersAdapter(){

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = this.users.get(position);
        holder.bindView(user, onUsersItemClickListener);
    }

    @Override
    public int getItemCount() {
        if (users == null) {
            return 0;
        }
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivUserAvatar)
        ImageView ivUserAvatar;
        @BindView(R.id.tvUserName)
        TextView tvUserName;
        @BindView(R.id.tvUserInfo)
        TextView tvUserInfo;
        @BindView(R.id.constraintLayout)
        ConstraintLayout constraintLayout;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Users user, OnUsersItemClickListener onUsersItemClickListener) {
            if (user.getNodeId()!= null)
                tvUserInfo.setText(user.getNodeId());
            if (user.getLogin() != null) {
                tvUserName.setText(user.getLogin());
            }
            constraintLayout.setOnClickListener(v -> {
                onUsersItemClickListener.userClicked(user.getLogin());
            });

            Picasso.with(ivUserAvatar.getContext()).load(user.getAvatarUrl()).into(ivUserAvatar);
        }
    }

    public interface OnUsersItemClickListener{
        void userClicked(String userName);
    }
}
