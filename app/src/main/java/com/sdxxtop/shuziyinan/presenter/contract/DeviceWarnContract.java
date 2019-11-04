package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EarlyWarningBean;

/**
 * 用来copy使用的
 */
public interface DeviceWarnContract {
    interface IView extends BaseView {

        void showData(EarlyWarningBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
