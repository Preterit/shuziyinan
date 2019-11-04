package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.DeviceListBean;

/**
 * 用来copy使用的
 */
public interface DeviceListContract {
    interface IView extends BaseView {

        void showData(DeviceListBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
