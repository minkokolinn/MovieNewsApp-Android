package com.example.admin.movienewsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.movienewsapp.R;
import com.example.admin.movienewsapp.adapters.CastAdapter;
import com.example.admin.movienewsapp.delegates.CastDelegate;
import com.example.admin.movienewsapp.models.Cast;
import com.example.admin.movienewsapp.models.MyCast;
import com.example.admin.movienewsapp.models.Results;
import com.example.admin.movienewsapp.networks.RetrofitHelper;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 9/26/2018.
 */

public class ShowActivity extends AppCompatActivity{
    ImageView ivBackDrop,ivPoster;
    TextView tvName,tvImdb,tvRelease,tvOverview;
    ImageButton ib;
    RetrofitHelper retrofitHelper;
    RecyclerView rv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_movie_activity);
        ivBackDrop=findViewById(R.id.img_backdrop_sa);
        ivPoster=findViewById(R.id.img_poster_sa);
        tvName=findViewById(R.id.tv_name_sa);
        tvImdb=findViewById(R.id.tv_imdb_sa);
        tvRelease=findViewById(R.id.tv_release_sa);
        tvOverview=findViewById(R.id.tv_overview_sa);
        ib=findViewById(R.id.ib_play_youtube);
        rv=findViewById(R.id.rv_casts);
        rv.setLayoutManager(new LinearLayoutManager(ShowActivity.this,LinearLayoutManager.HORIZONTAL,false));


        Intent i=getIntent();
        Bundle bd=i.getExtras();
        final Results results=bd.getParcelable("result");

        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"+results.getBackdrop_path()).placeholder(R.drawable.forest).into(ivBackDrop);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"+results.getPoster_path()).placeholder(R.drawable.forest).into(ivPoster);

        tvName.setText(results.getTitle());
        tvImdb.setText(results.getVote_count());
        tvRelease.setText(results.getVote_average());
        tvOverview.setText(results.getOverview());


        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=results.getId();
                Intent i=new Intent(ShowActivity.this,YouTubeActivity.class);
                Bundle bd=new Bundle();
                bd.putString("id",id);
                i.putExtras(bd);
                startActivity(i);
            }
        });

        retrofitHelper=RetrofitHelper.getINS();
        retrofitHelper.TakeCast(new CastDelegate() {
            @Override
            public void getCast(MyCast cast) {
                List<Cast>casts=cast.getCast();
                CastAdapter adapter=new CastAdapter(ShowActivity.this,casts);
                rv.setAdapter(adapter);
            }

            @Override
            public void onError(String str) {
                Toast.makeText(ShowActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        }, results.getId());
    }
}
