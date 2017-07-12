package com.marvin.splashedink.network;

import com.marvin.splashedink.bean.PhotoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface NetWorkService {
    @GET("photos")
    Observable<List<PhotoBean>> getPhotoList(@Query("client_id") String client_id,
                                             @Query("page") int page,
                                             @Query("per_page") int per_page);
}
