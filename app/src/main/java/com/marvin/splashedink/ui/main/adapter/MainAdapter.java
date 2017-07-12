package com.marvin.splashedink.ui.main.adapter;

import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.marvin.splashedink.R;
import com.marvin.splashedink.bean.PhotoBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MainAdapter extends BaseQuickAdapter<PhotoBean, BaseViewHolder> {
    public MainAdapter(@LayoutRes int layoutResId, @Nullable List<PhotoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoBean item) {
        helper.setText(R.id.name, item.getUser().getName());
        SimpleDraweeView image = helper.getView(R.id.item_image);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getUrls().getRegular()))
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(image.getController())
                .setAutoPlayAnimations(true)
                .build();
        image.setController(controller);
    }
}
