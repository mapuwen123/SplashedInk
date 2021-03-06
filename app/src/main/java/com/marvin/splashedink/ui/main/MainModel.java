package com.marvin.splashedink.ui.main;


import com.marvin.splashedink.MyApplication;
import com.marvin.splashedink.bean.PhotoBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MainModel {
    public Observable getPhotos(int page, int per_page) {
        Observable<List<PhotoBean>> observable = MyApplication.retrofitService.getPhotoList(page
                , per_page);
        return observable;
    }

}
