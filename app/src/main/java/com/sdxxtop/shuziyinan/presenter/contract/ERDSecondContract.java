package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;

/**
 * EventReportDetailSecondContract
 */
public interface ERDSecondContract {
    interface IView extends BaseView {
        void modifyRefresh();
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
