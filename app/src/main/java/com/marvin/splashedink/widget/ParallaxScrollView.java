package com.marvin.splashedink.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/7/13.
 * 视差滚动
 */

public class ParallaxScrollView extends ScrollView {
    public ParallaxScrollView(Context context) {
        super(context);
    }

    public ParallaxScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private ScrollviewListener mScrollViewListener = null;

    public void setScrollViewListener(ScrollviewListener listener) {
        this.mScrollViewListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface ScrollviewListener {
        void onScrollChanged(ParallaxScrollView scrollView, int x, int y, int oldx, int oldy);
    }
}
