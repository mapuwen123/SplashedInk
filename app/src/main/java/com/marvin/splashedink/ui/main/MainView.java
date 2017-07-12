package com.marvin.splashedink.ui.main;

import com.marvin.splashedink.base.BaseView;
import com.marvin.splashedink.bean.PhotoBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface MainView extends BaseView {
    void UpData(List<PhotoBean> data);
}
