package com.marvin.splashedink.base;

/**
 * Created by Administrator on 2017/6/12.
 */

public abstract class BasePresenter<V> {
    public V mView;

    public void attach(V mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }
}
