package com.github.gitusersapp.mvp.userdetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.gitusersapp.AndroidApplication;
import com.github.gitusersapp.R;
import com.github.gitusersapp.model.responce.User;
import com.github.gitusersapp.mvp.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

import static com.github.gitusersapp.mvp.start.StartActivity.USER_DETAIL_FR;

/**
 * /**
 * Created by Andriy Lykhtey on 2019-07-23.
 */
public class UserDetailFragment extends BaseFragment implements UserDetailView {

    private String userName;

    @BindView(R.id.ivDetailUserAvatar)
    CircleImageView ivDetailUserAvatar;
    @BindView(R.id.tvDetailUserName)
    TextView tvDetailUserName;
    @BindView(R.id.tvDetailUserCompany)
    TextView tvDetailUserCompany;
    @BindView(R.id.tvDetailUserLocation)
    TextView tvDetailUserLocation;
    @BindView(R.id.tvDetailUserBlog)
    TextView tvDetailUserBlog;

    @InjectPresenter
    UserDetailPresenter presenter;

    @ProvidePresenter
    public UserDetailPresenter providePresenter() {
        return AndroidApplication.startComponent().provideUserDetailPresenter();
    }

    public static UserDetailFragment newInstance(String userName) {

        Bundle args = new Bundle();
        args.putString(USER_DETAIL_FR, userName);
        UserDetailFragment fragment = new UserDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidApplication.applicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.userName = getArguments().getString(USER_DETAIL_FR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter.getUser(userName);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void userInfo(User user) {
        initViews(user);
    }

    @Override
    public void toggleLoading(boolean visible, int messageId) {

    }

    private void initViews(User user) {
        if (getContext() != null)
            Picasso.with(getContext()).load(user.getAvatarUrl()).into(ivDetailUserAvatar);
        tvDetailUserName.setText(user.getName());
        tvDetailUserCompany.setText(user.getCompany());
        tvDetailUserLocation.setText(user.getLocation());
        tvDetailUserBlog.setText(user.getBlog());
        tvDetailUserBlog.setTextColor(getResources().getColor(R.color.btn_search_user_color));
        tvDetailUserBlog.setOnClickListener(v -> {
            Uri uri = Uri.parse(user.getBlog());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }

}
