package com.sdxxtop.shuziyinan.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdxxtop.shuziyinan.R;
import com.sdxxtop.shuziyinan.model.bean.EventIndexBean;
import com.sdxxtop.shuziyinan.ui.activity.EventReportDetailActivity;
import com.sdxxtop.shuziyinan.utils.Date2Util;

public class EventReportListAdapter extends BaseQuickAdapter<EventIndexBean.EventBean, BaseViewHolder> {
    public EventReportListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventIndexBean.EventBean item) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvAddress = helper.getView(R.id.tv_address);
        TextView tvStatus = helper.getView(R.id.tv_status);

        tvTitle.setText(item.getTitle());
        tvTime.setText("上报时间：" + Date2Util.handleTime(item.getAdd_time()));
        tvAddress.setText("事件地点：" + item.getPlace());
        String strStatus = "";
        if (item.getIs_claim()==1){
            switch (item.getStatus()) {
                case 2:
                    strStatus = "已认领";
                    break;
                case 3:
                    strStatus = "待评价";
                    break;
                case 4:
                    strStatus = "已完成";
                    break;
                default:
                    strStatus = "待认领";
                    break;
            }
        }else{
            switch (item.getStatus()) { //状态(1:带派发 2:待解决 3:待验收 4:验收通过 5:验收不通过)
                case 2:
                    strStatus = "已派发";
                    break;
                case 3:
                    strStatus = "已反馈";
                    break;
                case 4:
                    strStatus = "已完成";
                    break;
                case 5:
//                strStatus = "验收不通过";
                    strStatus = "已完成";
                    break;
                default:
                    strStatus = "待派发";
                    break;
            }
        }

        tvStatus.setText(strStatus);

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventReportDetailActivity.startDetailActivity(v.getContext(), String.valueOf(item.getEvent_id()));
            }
        });
    }
}
