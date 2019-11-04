package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EventReadIndexBean;

public interface EventReportDetailContract {
    interface IView extends BaseView {
        void readData(EventReadIndexBean eventReadBean);

        void modifyRefresh();
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
