package com.marvin.splashedink.base;

/**
 * Created by Administrator on 2017/6/12.
 */

public abstract interface BaseView {
    void showProgress();
    void hideProgress();
    void error(String err);
}
