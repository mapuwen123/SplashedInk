package com.marvin.splashedink.ui.particulars;

import com.marvin.splashedink.base.BaseView;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface ParticularsView extends BaseView {
    void shareSuccess(String msg);
    void setAuthorHeader(String uri);
    void setAuthorName(String name);
    void setCreateTime(String time);
    void setSize(String size);
    void setShutterTime(String time);
    void setColor(String color);
    void setAperture(String aperture);
    void setAddr(String addr);
    void setFocal(String focal);
    void setCameraName(String name);
    void setExposure(String exposure);
    void setLikes(String likes);
    void setViews(String views);
    void setDownloads(String downloads);
    void setDownloadUrl(String url);
}
