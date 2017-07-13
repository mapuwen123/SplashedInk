package com.marvin.splashedink.network;

import com.marvin.splashedink.bean.PhotoBean;
import com.marvin.splashedink.bean.PhotoStatusBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface NetWorkService {
    @GET("photos?client_id=f7f954a8249404b33049059a8cce4b5147303f10b2089965dc0232c29d02f653")
    Observable<List<PhotoBean>> getPhotoList(@Query("page") int page,
                                             @Query("per_page") int per_page);

    @GET("photos/{photoId}?client_id=f7f954a8249404b33049059a8cce4b5147303f10b2089965dc0232c29d02f653")
    Observable<PhotoStatusBean> getPhotoStatus(@Path("photoId") String photoId);
}
