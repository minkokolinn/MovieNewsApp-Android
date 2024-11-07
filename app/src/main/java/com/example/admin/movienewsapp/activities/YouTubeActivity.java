package com.example.admin.movienewsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.movienewsapp.R;
import com.example.admin.movienewsapp.delegates.VideoDelegate;
import com.example.admin.movienewsapp.models.MyVideo;
import com.example.admin.movienewsapp.models.Trailer;
import com.example.admin.movienewsapp.networks.RetrofitHelper;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.ui.DefaultPlayerUIController;

import java.util.List;

/**
 * Created by Admin on 9/28/2018.
 */

public class YouTubeActivity extends AppCompatActivity{
    YouTubePlayerView player;
    RetrofitHelper retrofitHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_activity);
        player=findViewById(R.id.youtube_player);

        Intent i=getIntent();
        Bundle bd=i.getExtras();
        String id=bd.getString("id");

        retrofitHelper=RetrofitHelper.getINS();
        retrofitHelper.TakeVideo(new VideoDelegate() {
            @Override
            public void getData(MyVideo video) {
                List<Trailer> trailers=video.getResults();
                String key=trailers.get(0).getKey();
                PlayVideo(key);
            }

            @Override
            public void onError(String str) {

            }
        },id);

    }

    public void PlayVideo(final String key){
        getLifecycle().addObserver(player);
        player.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer youTubePlayer) {
                youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        youTubePlayer.loadVideo(key,0);
                    }
                });

            }
        },true);
    }
}
