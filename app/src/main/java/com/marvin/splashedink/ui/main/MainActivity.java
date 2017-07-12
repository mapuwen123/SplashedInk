package com.marvin.splashedink.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.marvin.splashedink.R;
import com.marvin.splashedink.base.BaseActivity;
import com.marvin.splashedink.bean.PhotoBean;
import com.marvin.splashedink.ui.main.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView,
        BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private MainAdapter mainAdapter;
    private List<PhotoBean> data;

    private int page = 1;
    private int per_page = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setIcon(R.drawable.logo);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void dataInit() {
        data = new ArrayList<>();
        mainAdapter = new MainAdapter(R.layout.main_item, data);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mainAdapter);

        presenter.getPhotos(page, per_page);
    }

    @Override
    protected void eventInit() {
        mainAdapter.setOnLoadMoreListener(this);
        swipe.setOnRefreshListener(this);
        recycler.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView滑动状态，滑动停止时加载图片
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Fresco.getImagePipeline().resume();
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Fresco.getImagePipeline().resume();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Fresco.getImagePipeline().pause();
                        break;
                }
            }
        });
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe.setRefreshing(false);
    }

    @Override
    public void error(String err) {

    }

    @Override
    public void UpData(List<PhotoBean> data) {
        if (data.size() != 0) {
            if (page == 1) {
                this.data.clear();
                this.data.addAll(data);
                mainAdapter.notifyDataSetChanged();
            } else {
                mainAdapter.addData(data);
            }
            if (data.size() < 10) {
                mainAdapter.loadMoreEnd();
            } else {
                mainAdapter.loadMoreComplete();
            }
        } else {
            mainAdapter.loadMoreEnd();
        }
    }

    //    加载更多监听
    @Override
    public void onLoadMoreRequested() {
        page = ++page;
        presenter.getPhotos(page, per_page);
    }

    //    刷新监听
    @Override
    public void onRefresh() {
        page = 1;
        presenter.getPhotos(page, per_page);
    }
}
