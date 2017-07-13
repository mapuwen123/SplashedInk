package com.marvin.splashedink.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.marvin.splashedink.R;
import com.marvin.splashedink.bean.PhotoBean;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MainAdapter extends BaseQuickAdapter<PhotoBean, BaseViewHolder> {
    private Context context;

    public MainAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<PhotoBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoBean item) {
        helper.setText(R.id.name, item.getUser().getName());
        FrameLayout background = helper.getView(R.id.background);
        ImageView image = helper.getView(R.id.item_image);
        background.setBackgroundColor(Color.parseColor(item.getColor()));
        Glide.with(context)
                .load(item.getUrls().getRegular())
                .transition(withCrossFade())
                .into(image);
    }
}
