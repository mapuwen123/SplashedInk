package com.marvin.splashedink.ui.particulars;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.Window;

import com.facebook.drawee.view.SimpleDraweeView;
import com.marvin.splashedink.R;
import com.marvin.splashedink.base.BaseActivity;

import butterknife.BindView;

public class ParticularsActivity extends BaseActivity<ParticularsView, ParticularsPresenter> implements ParticularsView {

    @BindView(R.id.image)
    SimpleDraweeView image;

    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected int getLayoutId() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());//new Slide()  new Fade()
        return R.layout.activity_particulars;
    }

    @Override
    public ParticularsPresenter initPresenter() {
        return new ParticularsPresenter();
    }

    @Override
    protected void actionbarInit() {

    }

    @Override
    protected void dataInit() {
        uri = getIntent().getStringExtra("URI");

        image.setImageURI(uri);
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
