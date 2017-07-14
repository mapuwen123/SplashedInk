package com.marvin.splashedink.ui.particulars;

import android.app.Activity;

import com.marvin.splashedink.base.BasePresenter;
import com.marvin.splashedink.bean.DownLoadBean;
import com.marvin.splashedink.bean.PhotoStatusBean;
import com.marvin.splashedink.common.BuildConfig;
import com.marvin.splashedink.utils.EmptyUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ParticularsPresenter extends BasePresenter<ParticularsView> implements UMShareListener {
    private ParticularsModel model;

    public ParticularsPresenter() {
        model = new ParticularsModel();
    }

    public void getPhotoStatus(String photoId) {
        model.getPhotoStatus(photoId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProFileObservable());
    }

    public void getDownloadUrl(String photoId) {
        mView.showProgress();
        model.getDownloadUrl(photoId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DownLoadUrlObservable());
    }

    public void shareImage(Activity activity, String url) {
        UMImage image = new UMImage(activity, url);
        new ShareAction(activity).withMedia(image).setDisplayList(SHARE_MEDIA.SINA,
                SHARE_MEDIA.QQ,
                SHARE_MEDIA.QZONE,
                SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.ALIPAY,
                SHARE_MEDIA.EMAIL,
                SHARE_MEDIA.SMS)
                .setCallback(this).open();
    }

    // 解除订阅
    private Disposable disposable;

    private class ProFileObservable implements Observer<PhotoStatusBean> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(@NonNull PhotoStatusBean photoStatusBean) {
            if (EmptyUtils.isNotEmpty(photoStatusBean.getUser())) {
                if (EmptyUtils.isNotEmpty(photoStatusBean.getUser().getProfile_image()))
                    if (EmptyUtils.isNotEmpty(photoStatusBean.getUser().getProfile_image().getMedium()))
                        mView.setAuthorHeader(photoStatusBean.getUser().getProfile_image().getMedium());
                if (EmptyUtils.isNotEmpty(photoStatusBean.getUser().getName()))
                    mView.setAuthorName(photoStatusBean.getUser().getName());
            }

            if (EmptyUtils.isNotEmpty(photoStatusBean.getCreated_at().substring(0, 9)))
                mView.setCreateTime(photoStatusBean.getCreated_at().substring(0, 9));

            if (EmptyUtils.isNotEmpty(photoStatusBean.getWidth()))
                if (EmptyUtils.isNotEmpty(photoStatusBean.getHeight()))
                    mView.setSize(photoStatusBean.getWidth() + "*" + photoStatusBean.getHeight());

            if (EmptyUtils.isNotEmpty(photoStatusBean.getExif())) {
                if (EmptyUtils.isNotEmpty(photoStatusBean.getExif().getExposure_time()))
                    mView.setShutterTime(photoStatusBean.getExif().getExposure_time());
                if (EmptyUtils.isNotEmpty(photoStatusBean.getExif().getAperture()))
                    mView.setAperture(photoStatusBean.getExif().getAperture());
                if (EmptyUtils.isNotEmpty(photoStatusBean.getExif().getFocal_length()))
                    mView.setFocal(photoStatusBean.getExif().getFocal_length());
                if (EmptyUtils.isNotEmpty(photoStatusBean.getExif().getModel()))
                    mView.setCameraName(photoStatusBean.getExif().getModel());
                if (EmptyUtils.isNotEmpty(String.valueOf(photoStatusBean.getExif().getIso())))
                    mView.setExposure(String.valueOf(photoStatusBean.getExif().getIso()));
            }

            if (EmptyUtils.isNotEmpty(photoStatusBean.getColor()))
                mView.setColor(photoStatusBean.getColor());

            if (EmptyUtils.isNotEmpty(photoStatusBean.getLocation()))
                if (EmptyUtils.isNotEmpty(photoStatusBean.getLocation().getTitle()))
                    mView.setAddr(photoStatusBean.getLocation().getTitle());

            if (EmptyUtils.isNotEmpty(String.valueOf(photoStatusBean.getLikes())))
                mView.setLikes(String.valueOf(photoStatusBean.getLikes()));

            if (EmptyUtils.isNotEmpty(String.valueOf(photoStatusBean.getViews())))
                mView.setViews(String.valueOf(photoStatusBean.getViews()));

            if (EmptyUtils.isNotEmpty(String.valueOf(photoStatusBean.getDownloads())))
                mView.setDownloads(String.valueOf(photoStatusBean.getDownloads()));
        }

        @Override
        public void onError(@NonNull Throwable e) {
            mView.error(e.getMessage());
            disposable.dispose();
        }

        @Override
        public void onComplete() {
            disposable.dispose();
        }
    }

    public class DownLoadUrlObservable implements Observer<DownLoadBean> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(@NonNull DownLoadBean downLoadBean) {
            mView.hideProgress();
            String download_url = downLoadBean.getUrl();
            mView.setDownloadUrl(download_url);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            mView.error(e.getMessage());
            disposable.dispose();
            mView.hideProgress();
        }

        @Override
        public void onComplete() {
            disposable.dispose();
            mView.hideProgress();
        }
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        mView.shareSuccess("正在进行跳转请稍后...");
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        mView.shareSuccess("分享成功");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        mView.error(throwable.getMessage());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        mView.error("取消分享");
    }
}
