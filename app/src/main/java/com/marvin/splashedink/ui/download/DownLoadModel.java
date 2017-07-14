package com.marvin.splashedink.ui.download;

import com.marvin.splashedink.bean.DiskDownloadBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by Administrator on 2017/7/13.
 */

public class DownLoadModel {
    public List<DiskDownloadBean> getDiskDownloads() {
        RealmQuery<DiskDownloadBean> query = Realm.getDefaultInstance().where(DiskDownloadBean.class);
        List<DiskDownloadBean> datas = query.findAll();
        return datas;
    }
}
