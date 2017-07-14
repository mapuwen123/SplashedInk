package com.marvin.splashedink.ui.download;

import com.marvin.splashedink.base.BaseView;
import com.marvin.splashedink.bean.DiskDownloadBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public interface DownLoadView extends BaseView {
    void onUpdata(List<DiskDownloadBean> data);
}
