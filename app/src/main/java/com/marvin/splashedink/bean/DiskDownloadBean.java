package com.marvin.splashedink.bean;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/7/14.
 */

public class DiskDownloadBean extends RealmObject {
    private long download_id;
    private String photo_id;
    private String url;
    private String path;
    private String preview_url;

    //    private int progress;
//    private long size;
    public long getDownload_id() {
        return download_id;
    }

    public void setDownload_id(long download_id) {
        this.download_id = download_id;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }
}
