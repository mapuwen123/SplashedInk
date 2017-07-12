package com.marvin.splashedink.ui.particulars;

import com.marvin.splashedink.base.BasePresenter;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ParticularsPresenter extends BasePresenter<ParticularsView> {
    private ParticularsModel model;

    public ParticularsPresenter() {
        model = new ParticularsModel();
    }
}
