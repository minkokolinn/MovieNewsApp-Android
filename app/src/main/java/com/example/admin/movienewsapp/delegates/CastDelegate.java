package com.example.admin.movienewsapp.delegates;

import com.example.admin.movienewsapp.models.Cast;
import com.example.admin.movienewsapp.models.MyCast;

/**
 * Created by Admin on 9/28/2018.
 */

public interface CastDelegate {
    void getCast(MyCast cast);
    void onError(String str);
}
