package com.example.admin.movienewsapp.networks;

import com.example.admin.movienewsapp.delegates.CastDelegate;
import com.example.admin.movienewsapp.delegates.MyDelegate;
import com.example.admin.movienewsapp.delegates.VideoDelegate;
import com.example.admin.movienewsapp.models.Film;
import com.example.admin.movienewsapp.models.MyCast;
import com.example.admin.movienewsapp.models.MyVideo;
import com.example.admin.movienewsapp.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 9/26/2018.
 */

public class RetrofitHelper {
    private static RetrofitHelper INS;

    public static RetrofitHelper getINS(){
        if(INS==null){
            INS=new RetrofitHelper();
        }
        return INS;
    }
    ApiService api;
    private RetrofitHelper(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api=retrofit.create(ApiService.class);
    }

    public void TakeTopRated(final MyDelegate delegate){
        api.getTopRated().enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if(response.isSuccessful()){
                    delegate.onGetData(response.body());
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });
    }

    public void TakePopular(final MyDelegate delegate){
        api.getPopular().enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if(response.isSuccessful()){
                    delegate.onGetData(response.body());
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });
    }

    public void TakeLatest(final MyDelegate delegate){
        api.getLatest().enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if(response.isSuccessful()){
                    delegate.onGetData(response.body());
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });

    }

    public void TakeVideo(final VideoDelegate delegate, String id){
        api.getVideo(id).enqueue(new Callback<MyVideo>() {
            @Override
            public void onResponse(Call<MyVideo> call, Response<MyVideo> response) {
                delegate.getData(response.body());
            }

            @Override
            public void onFailure(Call<MyVideo> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });
    }

    public void TakeCast(final CastDelegate delegate, String id){
        api.getCast(id).enqueue(new Callback<MyCast>() {
            @Override
            public void onResponse(Call<MyCast> call, Response<MyCast> response) {
                delegate.getCast(response.body());
            }

            @Override
            public void onFailure(Call<MyCast> call, Throwable t) {
                delegate.onError(t.getMessage());
            }
        });
    }
}
