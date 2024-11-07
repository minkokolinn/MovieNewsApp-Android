package com.example.admin.movienewsapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.movienewsapp.R;
import com.example.admin.movienewsapp.activities.ShowActivity;
import com.example.admin.movienewsapp.adapters.MovieAdapter;
import com.example.admin.movienewsapp.delegates.FilmDelegate;
import com.example.admin.movienewsapp.delegates.MyDelegate;
import com.example.admin.movienewsapp.models.Film;
import com.example.admin.movienewsapp.models.Results;
import com.example.admin.movienewsapp.networks.RetrofitHelper;

import java.util.List;

/**
 * Created by Admin on 9/27/2018.
 */

public class LatestFrag extends Fragment{
    RecyclerView rv;
    RetrofitHelper rh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.latest_frag,null);
        rv=v.findViewById(R.id.rv_lat_frag);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rh=RetrofitHelper.getINS();
        rh.TakeLatest(new MyDelegate() {
            @Override
            public void onGetData(Film film) {
                List<Results> results=film.getResults();
                MovieAdapter adapter=new MovieAdapter(getActivity(),results);
                rv.setAdapter(adapter);
                adapter.setOnFilmClick(new FilmDelegate() {
                    @Override
                    public void onFilmClick(Results results) {
                        Intent i=new Intent(getActivity(), ShowActivity.class);
                        Bundle bd=new Bundle();
                        bd.putParcelable("result",results);
                        i.putExtras(bd);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onError(String s) {

            }
        });
    }
}
