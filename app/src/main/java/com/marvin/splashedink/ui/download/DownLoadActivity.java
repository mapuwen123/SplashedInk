package com.marvin.splashedink.ui.download;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.marvin.splashedink.R;
import com.marvin.splashedink.base.BaseActivity;
import com.marvin.splashedink.bean.DiskDownloadBean;
import com.marvin.splashedink.ui.download.adapter.DownLoadAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DownLoadActivity extends BaseActivity<DownLoadView, DownLoadPresenter> implements DownLoadView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private DownLoadAdapter adapter;

    private List<DiskDownloadBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_down_load;
    }

    @Override
    public DownLoadPresenter initPresenter() {
        return new DownLoadPresenter();
    }

    @Override
    protected void actionbarInit() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void dataInit() {
        data = new ArrayList<>();
        adapter = new DownLoadAdapter(this, R.layout.download_item, data);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        presenter.getDiskDownloads();
    }

    @Override
    protected void eventInit() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void error(String err) {

    }

    @Override
    public void onUpdata(List<DiskDownloadBean> data) {
        adapter.addData(data);
    }
}
