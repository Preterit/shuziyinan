package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.AuthDataBean;

/**
 * 用来copy使用的
 */
public interface DataMonitoringContract {
    interface IView extends BaseView {

        void showData(AuthDataBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
