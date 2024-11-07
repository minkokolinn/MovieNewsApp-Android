package com.example.admin.movienewsapp.networks;

import com.example.admin.movienewsapp.models.Film;
import com.example.admin.movienewsapp.models.MyCast;
import com.example.admin.movienewsapp.models.MyVideo;
import com.example.admin.movienewsapp.utils.AppConstant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Admin on 9/25/2018.
 */

public interface ApiService {
    @GET(AppConstant.Top_Rated)
    Call<Film> getTopRated();

    @GET(AppConstant.Popular)
    Call<Film> getPopular();

    @GET(AppConstant.Latest)
    Call<Film> getLatest();

    @GET("3/movie/{a}/videos?api_key=78a04746f32a9808bd02b8eed323d025")
    Call<MyVideo> getVideo(@Path("a")String id);

    @GET("3/movie/{a}/casts?api_key=78a04746f32a9808bd02b8eed323d025")
    Call<MyCast> getCast(@Path("a")String id);
}
