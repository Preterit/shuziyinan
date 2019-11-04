package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.DeviceDataBean;

/**
 * 用来copy使用的
 */
public interface DeviceDataDetailContract {
    interface IView extends BaseView {

        void showData(DeviceDataBean bean);

        void showListData(DeviceDataBean bean,int pageSize);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
