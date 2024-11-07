package com.example.admin.movienewsapp.delegates;

import com.example.admin.movienewsapp.models.Film;

/**
 * Created by Admin on 9/26/2018.
 */

public interface MyDelegate {

    void onGetData(Film film);
    void onError(String s);
}
