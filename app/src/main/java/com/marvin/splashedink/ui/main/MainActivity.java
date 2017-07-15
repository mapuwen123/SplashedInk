package com.marvin.splashedink.ui.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.marvin.splashedink.R;
import com.marvin.splashedink.base.BaseActivity;
import com.marvin.splashedink.bean.PhotoBean;
import com.marvin.splashedink.ui.download.DownLoadActivity;
import com.marvin.splashedink.ui.main.adapter.MainAdapter;
import com.marvin.splashedink.ui.particulars.ParticularsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView,
        BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener,
        Toolbar.OnMenuItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MainAdapter mainAdapter;
    private List<PhotoBean> data;

    private int page = 1;
    private int per_page = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    protected void actionbarInit() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    protected void dataInit() {
        data = new ArrayList<>();
        mainAdapter = new MainAdapter(this, R.layout.main_item, data);
        mainAdapter.setEnableLoadMore(true);
        mainAdapter.openLoadAnimation();

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(mainAdapter);

        presenter.getPhotos(page, per_page);
    }

    @Override
    protected void eventInit() {
        mainAdapter.setOnLoadMoreListener(this);
        mainAdapter.setOnItemClickListener(this);
        swipe.setOnRefreshListener(this);
        recycler.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView滑动状态，滑动停止时加载图片
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Glide.with(MainActivity.this).resumeRequests();
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Glide.with(MainActivity.this).resumeRequests();
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Glide.with(MainActivity.this).pauseRequests();
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
        showToast(err);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(this, view, "image");
        Intent intent = new Intent(this, ParticularsActivity.class);
        intent.putExtra("PHOTO_ID", data.get(position).getId());
        intent.putExtra("IMAGE_URL", data.get(position).getUrls().getRegular());
        intent.putExtra("HEIGHT", view.getHeight());
        startActivity(intent, options.toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawload_item:
                startIntent(DownLoadActivity.class, null);
                break;
        }
        return true;
    }
}
