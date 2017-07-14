package com.marvin.splashedink.ui.particulars;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.marvin.splashedink.R;
import com.marvin.splashedink.base.BaseActivity;
import com.marvin.splashedink.bean.DiskDownloadBean;
import com.marvin.splashedink.common.BuildConfig;
import com.marvin.splashedink.widget.ParallaxScrollView;

import java.io.File;

import butterknife.BindView;
import io.realm.Realm;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ParticularsActivity extends BaseActivity<ParticularsView, ParticularsPresenter> implements ParticularsView,
        ParallaxScrollView.ScrollviewListener,
        View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.scroll)
    ParallaxScrollView scroll;
    @BindView(R.id.download)
    LinearLayout download;
    @BindView(R.id.share)
    LinearLayout share;
    @BindView(R.id.window)
    LinearLayout window;
    @BindView(R.id.text_size)
    TextView textSize;
    @BindView(R.id.size)
    LinearLayout size;
    @BindView(R.id.text_time)
    TextView textTime;
    @BindView(R.id.time)
    LinearLayout time;
    @BindView(R.id.text_color)
    TextView textColor;
    @BindView(R.id.view_color)
    View viewColor;
    @BindView(R.id.color)
    LinearLayout color;
    @BindView(R.id.text_aperture)
    TextView textAperture;
    @BindView(R.id.aperture)
    LinearLayout aperture;
    @BindView(R.id.text_addr)
    TextView textAddr;
    @BindView(R.id.addr)
    LinearLayout addr;
    @BindView(R.id.text_focal)
    TextView textFocal;
    @BindView(R.id.focal)
    LinearLayout focal;
    @BindView(R.id.text_camera)
    TextView textCamera;
    @BindView(R.id.camera)
    LinearLayout camera;
    @BindView(R.id.text_exposure)
    TextView textExposure;
    @BindView(R.id.exposure)
    LinearLayout exposure;
    @BindView(R.id.text_likes)
    TextView textLikes;
    @BindView(R.id.likes)
    LinearLayout likes;
    @BindView(R.id.text_views)
    TextView textViews;
    @BindView(R.id.views)
    LinearLayout views;
    @BindView(R.id.text_downloads)
    TextView textDownloads;
    @BindView(R.id.downloads)
    LinearLayout downloads;
    @BindView(R.id.header_image)
    ImageView headerImage;
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.create_time)
    TextView createTime;

    private String photo_id = "";
    private int height = 0;
    private String image_url = "";

    private ProgressDialog progress;

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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        toolbar.setTitle("");
    }

    @Override
    protected void dataInit() {
        photo_id = getIntent().getStringExtra("PHOTO_ID");
        height = getIntent().getIntExtra("HEIGHT", 0);
        image_url = getIntent().getStringExtra("IMAGE_URL");

        ViewGroup.LayoutParams params = image.getLayoutParams();
        params.height = height;
        Glide.with(this)
                .load(image_url)
                .transition(withCrossFade())
                .into(image);

        presenter.getPhotoStatus(photo_id);
    }

    @Override
    protected void eventInit() {
        scroll.setScrollViewListener(this);

        download.setOnClickListener(this);
        share.setOnClickListener(this);
        window.setOnClickListener(this);
    }

    private void showDialog() {
        progress = new ProgressDialog(this);
        progress.setMessage("下载准备中,请稍后...");
        progress.show();
    }

    @Override
    public void showProgress() {
        showDialog();
    }

    @Override
    public void hideProgress() {
        progress.dismiss();
    }

    @Override
    public void error(String err) {
        showToast(err);
    }

    @Override
    public void shareSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void setAuthorHeader(String uri) {
        RequestOptions options = new RequestOptions();
        options.circleCrop();
        Glide.with(this)
                .load(uri)
                .apply(options)
                .transition(withCrossFade())
                .into(headerImage);
    }

    @Override
    public void setAuthorName(String name) {
        from.setText("来自于 " + name);
    }

    @Override
    public void setCreateTime(String time) {
        createTime.setText("创作于 " + time);
    }

    @Override
    public void setSize(String size) {
        textSize.setText(size);
    }

    @Override
    public void setShutterTime(String time) {
        textTime.setText(time);
    }

    @Override
    public void setColor(String color) {
        textColor.setText(color);
        viewColor.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void setAperture(String aperture) {
        textAperture.setText(aperture);
    }

    @Override
    public void setAddr(String addr) {
        textAddr.setText(addr);
    }

    @Override
    public void setFocal(String focal) {
        textFocal.setText(focal);
    }

    @Override
    public void setCameraName(String name) {
        textCamera.setText(name);
    }

    @Override
    public void setExposure(String exposure) {
        textExposure.setText(exposure);
    }

    @Override
    public void setLikes(String likes) {
        textLikes.setText(likes);
    }

    @Override
    public void setViews(String views) {
        textViews.setText(views);
    }

    @Override
    public void setDownloads(String downloads) {
        textDownloads.setText(downloads);
    }

    @Override
    public void setDownloadUrl(String url) {
        File destDir = new File(BuildConfig.AppDir + "/Download");
        if (!destDir.exists()) {// 判断文件夹是否存在
            destDir.mkdirs();
        }
        DownloadManager download = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        // 通知栏中将出现的内容
        request.setTitle(photo_id);
        // 下载过程和下载完成后通知栏有通知消息。
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE | DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        File fileName = new File(destDir, photo_id + ".jpg");
        request.setDestinationUri(Uri.fromFile(fileName));
        long id = download.enqueue(request);
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            DiskDownloadBean diskDownloadBean = realm.createObject(DiskDownloadBean.class);
            diskDownloadBean.setDownload_id(id);
            diskDownloadBean.setPhoto_id(photo_id);
            diskDownloadBean.setUrl(url);
            diskDownloadBean.setPath(fileName.getAbsolutePath());
            diskDownloadBean.setPreview_url(image_url);
        });
    }

    @Override
    public void onScrollChanged(ParallaxScrollView scrollView, int x, int y, int oldx, int oldy) {
        image.scrollTo(x, -y / 3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.download:
                presenter.getDownloadUrl(photo_id);
                break;
            case R.id.share:
                presenter.shareImage(this, image_url);
                break;
            case R.id.window:

                break;
        }
    }
}
