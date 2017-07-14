package com.marvin.splashedink.ui.download;

import com.marvin.splashedink.base.BasePresenter;
import com.marvin.splashedink.bean.DiskDownloadBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class DownLoadPresenter extends BasePresenter<DownLoadView> {
    private DownLoadModel model;

    public DownLoadPresenter() {
        model = new DownLoadModel();
    }

    public void getDiskDownloads() {
        List<DiskDownloadBean> data = model.getDiskDownloads();
        mView.onUpdata(data);
    }
}
