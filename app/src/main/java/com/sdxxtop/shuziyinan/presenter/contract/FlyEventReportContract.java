package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.FlyEventPartBean;

/**
 * 用来copy使用的
 */
public interface FlyEventReportContract {
    interface IView extends BaseView {

        void showPart(FlyEventPartBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
