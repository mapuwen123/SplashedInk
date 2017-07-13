package com.marvin.splashedink.ui.main;

import com.marvin.splashedink.base.BasePresenter;
import com.marvin.splashedink.bean.PhotoBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MainPresenter extends BasePresenter<MainView> implements Observer<List<PhotoBean>> {
    private MainModel model;

    public MainPresenter() {
        model = new MainModel();
    }

    public void getPhotos(int page, int per_page) {
        if (page == 1) {
            mView.showProgress();
        }
        model.getPhotos(page, per_page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    // 解除订阅
    private Disposable disposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(@NonNull List<PhotoBean> photoBeen) {
        mView.UpData(photoBeen);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        mView.error(e.getMessage());
        mView.hideProgress();
        disposable.dispose();
    }

    @Override
    public void onComplete() {
        mView.hideProgress();
        disposable.dispose();
    }
}
