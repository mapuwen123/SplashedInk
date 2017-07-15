package com.marvin.splashedink.ui.look;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.Window;
import android.view.WindowManager;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.marvin.splashedink.R;
import com.marvin.splashedink.base.BaseActivity;

import butterknife.BindView;

public class LookActivity extends BaseActivity<LookView, LookPresenter> implements LookView {
    @BindView(R.id.photo)
    PhotoView photo;

    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected int getLayoutId() {
        return R.layout.activity_look;
    }

    @Override
    public LookPresenter initPresenter() {
        return new LookPresenter();
    }

    @Override
    protected void actionbarInit() {

    }

    @Override
    protected void dataInit() {
        path = getIntent().getStringExtra("PHOTO_PATH");

        // 启用图片缩放功能
        photo.enable();
        // 获取/设置 最大缩放倍数
        photo.setMaxScale(3);

        Glide.with(this)
                .load(path)
                .into(photo);
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
