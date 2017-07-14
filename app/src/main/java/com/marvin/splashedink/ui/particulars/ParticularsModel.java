package com.marvin.splashedink.ui.particulars;


import com.marvin.splashedink.MyApplication;
import com.marvin.splashedink.bean.DownLoadBean;
import com.marvin.splashedink.bean.PhotoStatusBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ParticularsModel {
    public Observable getPhotoStatus(String photoId) {
        Observable<PhotoStatusBean> observable = MyApplication.retrofitService.getPhotoStatus(photoId);
        return observable;
    }

    public Observable getDownloadUrl(String photoId) {
        Observable<DownLoadBean> observable = MyApplication.retrofitService.getDownLoadUrl(photoId);
        return observable;
    }
}
