package com.marvin.splashedink.ui.download.adapter;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.marvin.splashedink.R;
import com.marvin.splashedink.bean.DiskDownloadBean;
import com.marvin.splashedink.common.BuildConfig;
import com.marvin.splashedink.ui.download.DownLoadActivity;
import com.marvin.splashedink.ui.look.LookActivity;
import com.marvin.splashedink.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.realm.Realm;
import io.realm.RealmResults;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Administrator on 2017/7/14.
 */

public class DownLoadAdapter extends BaseQuickAdapter<DiskDownloadBean, BaseViewHolder> {
    private DownLoadActivity activity;

    public DownLoadAdapter(DownLoadActivity activity, @LayoutRes int layoutResId, @Nullable List<DiskDownloadBean> data) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiskDownloadBean item) {
        helper.setText(R.id.text_photo_id, item.getPhoto_id());
        Glide.with(activity)
                .load(item.getPreview_url())
                .transition(withCrossFade())
                .into((ImageView) helper.getView(R.id.background));

        ImageView iv_down_status = helper.getView(R.id.iv_down_status);
        ImageView iv_down_reset_look = helper.getView(R.id.iv_down_reset_look);
        //接收事件可以在任何地方接收，不管该任务是否开始下载均可接收.
        Disposable disposable = RxDownload.getInstance(activity).receiveDownloadStatus(item.getUrl())
                .subscribe(event -> {
                    //当事件为Failed时, 才会有异常信息, 其余时候为null.
                    if (event.getFlag() == DownloadFlag.FAILED) {
                        Throwable throwable = event.getError();
                        Logger.w("Error", throwable);
                    }
                    if (event.getDownloadStatus().getFormatDownloadSize().equalsIgnoreCase(event.getDownloadStatus().getFormatTotalSize())) {
                        helper.setText(R.id.text_photo_id, item.getPhoto_id());
                        iv_down_status.setBackgroundResource(R.drawable.download_complete);
                        iv_down_reset_look.setBackgroundResource(R.drawable.download_look);
                        iv_down_reset_look.setTag(true);
                    } else {
                        helper.setText(R.id.text_photo_id, item.getPhoto_id() + ":" + event.getDownloadStatus().getPercent());
                        iv_down_status.setBackgroundResource(R.drawable.download_midway);
                        iv_down_reset_look.setBackgroundResource(R.drawable.download_reset);
                        iv_down_reset_look.setTag(false);
                    }
                });
        helper.getView(R.id.iv_down_reset_look).setOnClickListener(new adapterItemOnClick(item.getUrl(), item.getPhoto_id()));
        helper.getView(R.id.iv_down_close).setOnClickListener(new adapterItemOnClick(item.getUrl(), item.getPhoto_id()));
        RxDownload.getInstance(activity).getTotalDownloadRecords();
    }

    public class adapterItemOnClick implements View.OnClickListener {
        private String url;
        private String id;

        public adapterItemOnClick(String url, String id) {
            this.url = url;
            this.id = id;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_down_reset_look:
                    if (Boolean.valueOf(view.getTag().toString())) {
                        Intent intent = new Intent(activity, LookActivity.class);
                        intent.putExtra("PHOTO_PATH", BuildConfig.AppDir + "/Download/" + id + ".jpg");
                        activity.startActivity(intent);
                    } else {
                        RxDownload.getInstance(activity)
                                .serviceDownload(url, id + ".jpg")
                                .subscribe((Consumer<Object>) o -> {
                                    ToastUtil.getInstance(activity)
                                            .setDuration(Toast.LENGTH_SHORT)
                                            .setText("任务已加入下载队列")
                                            .show();
                                });
                    }
                    break;
                case R.id.iv_down_close:
                    RxDownload.getInstance(activity).deleteServiceDownload(url, true).subscribe();
                    RealmResults<DiskDownloadBean> results = Realm.getDefaultInstance().where(DiskDownloadBean.class)
                            .in("url", new String[]{url})
                            .findAll();
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        results.deleteAllFromRealm();
                    });
                    activity.getDiskDownloads();
                    break;
            }
        }
    }
}
