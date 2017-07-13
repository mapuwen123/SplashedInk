package com.marvin.splashedink.ui.download;

import android.os.Bundle;

import com.marvin.splashedink.R;
import com.marvin.splashedink.base.BaseActivity;

public class DownLoadActivity extends BaseActivity<DownLoadView, DownLoadPresenter> implements DownLoadView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_down_load;
    }

    @Override
    public DownLoadPresenter initPresenter() {
        return new DownLoadPresenter();
    }

    @Override
    protected void actionbarInit() {

    }

    @Override
    protected void dataInit() {

    }

    @Override
    protected void eventInit() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void error(String err) {

    }
}
