package com.sdxxtop.shuziyinan.ui.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdxxtop.shuziyinan.R;
import com.sdxxtop.shuziyinan.model.bean.MainIndexBeanNew;
import com.sdxxtop.shuziyinan.ui.activity.VideoPlay2Activity;
import com.sdxxtop.shuziyinan.ui.widget.RoundRectImageView;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author :  lwb
 * Date: 2019/9/5
 * Desc:
 */
public class HomeImageAdapter extends BaseQuickAdapter<MainIndexBeanNew.WheelPlantingVideo, BaseViewHolder> {

    public HomeImageAdapter(@Nullable List<MainIndexBeanNew.WheelPlantingVideo> data) {
        super(R.layout.item_only_img, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainIndexBeanNew.WheelPlantingVideo item) {
        RoundRectImageView ivIcon = helper.getView(R.id.iv_icon);
        ImageView ivPlayIcon = helper.getView(R.id.iv_play_icon);
        TextView tv_line_1 = helper.getView(R.id.tv_line_1);
        if (helper.getAdapterPosition() == 0) {
            tv_line_1.setVisibility(View.VISIBLE);
        } else {
            tv_line_1.setVisibility(View.GONE);
        }

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.bg_home_new);
        ivPlayIcon.setVisibility(item.video_id == 0 ? View.INVISIBLE : View.VISIBLE);

        Glide.with(mContext).load(item.cover_img)
                .apply(options)
                .into(ivIcon);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(item.video_link)) return;
                Intent intent = new Intent(mContext, VideoPlay2Activity.class);
                intent.putExtra("video_path", "" + item.video_link);
                mContext.startActivity(intent);
            }
        });
    }
}
