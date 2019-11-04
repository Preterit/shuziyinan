package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.DeviceMapBean;

/**
 * 用来copy使用的
 */
public interface MonitorMapContract {
    interface IView extends BaseView {

        void showMapInfo(DeviceMapBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
