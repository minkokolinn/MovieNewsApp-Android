package com.example.admin.movienewsapp.delegates;

import com.example.admin.movienewsapp.models.MyVideo;

/**
 * Created by Admin on 9/28/2018.
 */

public interface VideoDelegate {
    void getData(MyVideo video);
    void onError(String str);
}
