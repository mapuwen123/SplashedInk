package com.marvin.splashedink.ui.download.adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.marvin.splashedink.R;
import com.marvin.splashedink.bean.DiskDownloadBean;
import com.orhanobut.logger.Logger;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Administrator on 2017/7/14.
 */

public class DownLoadAdapter extends BaseQuickAdapter<DiskDownloadBean, BaseViewHolder> {
    private Context context;

    public DownLoadAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<DiskDownloadBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiskDownloadBean item) {
        helper.setText(R.id.text_photo_id, item.getPhoto_id());
        Glide.with(context)
                .load(item.getPreview_url())
                .transition(withCrossFade())
                .into((ImageView) helper.getView(R.id.background));
        long download_id = item.getDownload_id();
        query(download_id);
    }

    // 查询下载进度，文件总大小多少，已经下载多少
    private void query(long id) {
        DownloadManager.Query downloadQuery = new DownloadManager.Query();
        downloadQuery.setFilterById(id);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Cursor cursor = downloadManager.query(downloadQuery);
        if (cursor != null && cursor.moveToFirst()) {
            int fileName = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
            int fileUri = cursor.getColumnIndex(DownloadManager.COLUMN_URI);
            String fn = cursor.getString(fileName);
            String fu = cursor.getString(fileUri);

            int totalSizeBytesIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
            int bytesDownloadSoFarIndex = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);

            // 下载的文件总大小
            int totalSizeBytes = cursor.getInt(totalSizeBytesIndex);

            // 截止目前已经下载的文件总大小
            int bytesDownloadSoFar = cursor.getInt(bytesDownloadSoFarIndex);

            Logger.d(this.getClass().getName(),
                    "from " + fu + " 下载到本地 " + fn + " 文件总大小:" + totalSizeBytes + " 已经下载:" + bytesDownloadSoFar);

            cursor.close();
        }
    }
}
