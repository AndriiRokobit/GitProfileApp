package com.github.gitusersapp.mvp.start;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.gitusersapp.AndroidApplication;
import com.github.gitusersapp.R;
import com.github.gitusersapp.model.responce.Users;
import com.github.gitusersapp.mvp.base.BaseActivity;
import com.github.gitusersapp.mvp.start.adapter.UsersAdapter;
import com.github.gitusersapp.mvp.userdetail.UserDetailFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class StartActivity extends BaseActivity implements StartView, UsersAdapter.OnUsersItemClickListener{

    public static final String USER_DETAIL_FR = "userDetail";

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;
    @BindView(R.id.container_fragment)
    FrameLayout containerFragment;

    @Inject
    UsersAdapter adapter;

    @InjectPresenter
    StartPresenter presenter;

    @ProvidePresenter
    public StartPresenter providePresenter() {
        return AndroidApplication.startComponent().provideStartPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidApplication.applicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAdapter();
        changeStatusBar(R.color.status_bar_color);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void toggleLoading(boolean visible, int messageId) {

    }

    @Override
    public void usersList(List<Users> users) {
        adapter.setUserList(users);
    }

    @OnClick(R.id.btnSearch)
    public void getAllUsers(){
        presenter.searchUsers();
    }

    @Override
    public void userClicked(String userName) {
        containerFragment.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, UserDetailFragment.newInstance(userName))
                .setCustomAnimations( R.animator.slide_up, 0, 0, R.animator.slide_down)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
            containerFragment.setVisibility(View.GONE);
        }

    }

    private void initAdapter() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter.setUserList(null);
        adapter.setOnUsersItemClickListener(this);
        rvUsers.setAdapter(adapter);
    }

    protected void changeStatusBar(int colorId) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(colorId));
        }
    }
}
