package com.github.gitusersapp.mvp.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.gitusersapp.mvp.base.BaseFragment;
import com.mapbox.mapboxsdk.Mapbox;

import java.util.Objects;


public class MapFragment extends BaseFragment {

    private final String ACCESS_TOKEN = "pk.eyJ1IjoiYW5kcm9rIiwiYSI6ImNqeWcxdWZqYjAyamQzY3RlOWYxODBtcXcifQ.eWCpKcz95v--1jWR1Cakpw";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(Objects.requireNonNull(getContext()), ACCESS_TOKEN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
