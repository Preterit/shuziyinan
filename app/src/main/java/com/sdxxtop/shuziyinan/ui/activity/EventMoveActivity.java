package com.sdxxtop.shuziyinan.ui.activity;

import android.view.View;
import android.widget.Button;

import com.sdxxtop.shuziyinan.R;
import com.sdxxtop.shuziyinan.base.BaseMvpActivity;
import com.sdxxtop.shuziyinan.model.bean.EventShowBean;
import com.sdxxtop.shuziyinan.presenter.EventMovePresenter;
import com.sdxxtop.shuziyinan.presenter.contract.EventMoveContract;
import com.sdxxtop.shuziyinan.ui.widget.NumberEditTextView;
import com.sdxxtop.shuziyinan.ui.widget.TextAndTextView;
import com.sdxxtop.shuziyinan.ui.widget.timePicker.ProvinceTwoPickerView;

import butterknife.BindView;
import butterknife.OnClick;

public class EventMoveActivity extends BaseMvpActivity<EventMovePresenter> implements EventMoveContract.IView, ProvinceTwoPickerView.OnConfirmClick {

    @BindView(R.id.event_report)
    TextAndTextView eventReport;
    @BindView(R.id.et_num_content)
    NumberEditTextView etNumContent;
    @BindView(R.id.btn_check_success)
    Button btnCheckSuccess;

    private ProvinceTwoPickerView pickerUtil;
    private int selectPartId = 0;
    private String eventId;

    @Override
    protected int getLayout() {
        return R.layout.activity_event_move;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.loadData();
    }

    @Override
    protected void initView() {
        super.initView();
        eventId = getIntent().getStringExtra("eventId");
        etNumContent.setEditHint(" ");
    }

    @OnClick({R.id.event_report,R.id.btn_check_success})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.event_report:    // 选择流转部门
                hideKeyboard(eventReport);
                if (pickerUtil!=null){
                    pickerUtil.show();
                }
                break;
            case R.id.btn_check_success:   // 提交流转
                mPresenter.eventOperate(etNumContent.getEditValue(), selectPartId,eventId);
                break;
        }

    }

    @Override
    public void confirmClick(String resultTx, int resultId) {
        eventReport.getTextRightText().setText(resultTx);
        selectPartId = resultId;
    }

    @Override
    public void showData(EventShowBean bean) {
        pickerUtil = new ProvinceTwoPickerView(this, bean.getPart());
        pickerUtil.setOnConfirmClick(this);
    }
}
