package com.sdxxtop.shuziyinan.ui.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdxxtop.shuziyinan.R;
import com.sdxxtop.shuziyinan.model.bean.EventListBean;
import com.sdxxtop.shuziyinan.ui.activity.PartEventListActivity;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author :  lwb
 * Date: 2019/5/9
 * Desc:
 */
public class EventStatistyListAdapter extends BaseQuickAdapter<EventListBean.CompleteInfo, BaseViewHolder> {

    private String mStartTime,mEndTime;
    private int mStatus;

    public EventStatistyListAdapter(int layoutResId, @Nullable List<EventListBean.CompleteInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventListBean.CompleteInfo item) {

        helper.setText(R.id.event_num,String.valueOf(item.getCount()));
        helper.setText(R.id.part_name,String.valueOf(item.getPart_name()));

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PartEventListActivity.class);
                intent.putExtra("part_name", item.getPart_name());
                intent.putExtra("part_id", String.valueOf(item.getPart_id()));
                intent.putExtra("status", mStatus);
                intent.putExtra("startTime", mStartTime);
                intent.putExtra("endTime", mEndTime);

                mContext.startActivity(intent);
            }
        });
    }

    public void setTime(String startTime, String endTime,int status) {
        this.mStartTime = startTime;
        this.mEndTime = endTime;
        this.mStatus = status;
    }
}
