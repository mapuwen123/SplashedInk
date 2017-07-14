package com.marvin.splashedink.ui.main.adapter;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.marvin.splashedink.MyApplication;
import com.marvin.splashedink.R;
import com.marvin.splashedink.bean.DiskDownloadBean;
import com.marvin.splashedink.bean.DownLoadBean;
import com.marvin.splashedink.bean.PhotoBean;
import com.marvin.splashedink.common.BuildConfig;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MainAdapter extends BaseQuickAdapter<PhotoBean, BaseViewHolder> {
    private Context context;
    private ProgressDialog progress;

    public MainAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<PhotoBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoBean item) {
        helper.setText(R.id.name, item.getUser().getName());
        FrameLayout background = helper.getView(R.id.background);
        ImageView image = helper.getView(R.id.item_image);
        background.setBackgroundColor(Color.parseColor(item.getColor()));
        Glide.with(context)
                .load(item.getUrls().getRegular())
                .transition(withCrossFade())
                .into(image);
        ImageView download = helper.getView(R.id.download);
        download.setOnClickListener(view -> {
            showDialog();
            MyApplication.retrofitService.getDownLoadUrl(item.getId())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DownLoadUrlObservable(item.getId(), item.getUrls().getRegular()));
        });
    }

    private void showDialog() {
        progress = new ProgressDialog(context);
        progress.setMessage("下载准备中,请稍后...");
        progress.show();
    }

    // 解除订阅
    private Disposable disposable;

    public class DownLoadUrlObservable implements Observer<DownLoadBean> {
        private String photo_id;
        private String preview_url;

        public DownLoadUrlObservable(String photo_id, String preview_url) {
            this.photo_id = photo_id;
            this.preview_url = preview_url;
        }

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(@NonNull DownLoadBean downLoadBean) {
            progress.dismiss();
            String download_url = downLoadBean.getUrl();
            File destDir = new File(BuildConfig.AppDir + "/Download");
            if (!destDir.exists()) {// 判断文件夹是否存在
                destDir.mkdirs();
            }
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(download_url));
            // 通知栏中将出现的内容
            request.setTitle(photo_id);
            // 下载过程和下载完成后通知栏有通知消息。
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE | DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            File fileName = new File(destDir, photo_id + ".jpg");
            request.setDestinationUri(Uri.fromFile(fileName));
            long id = downloadManager.enqueue(request);
            Realm.getDefaultInstance().executeTransactionAsync(realm -> {
                DiskDownloadBean diskDownloadBean = realm.createObject(DiskDownloadBean.class);
                diskDownloadBean.setDownload_id(id);
                diskDownloadBean.setPhoto_id(photo_id);
                diskDownloadBean.setUrl(download_url);
                diskDownloadBean.setPath(fileName.getAbsolutePath());
                diskDownloadBean.setPreview_url(preview_url);
            });
        }

        @Override
        public void onError(@NonNull Throwable e) {
            disposable.dispose();
            progress.dismiss();
        }

        @Override
        public void onComplete() {
            disposable.dispose();
            progress.dismiss();
        }
    }
}
