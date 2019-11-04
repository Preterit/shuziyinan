package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EventListBean;
import com.sdxxtop.shuziyinan.model.bean.EventShowBean;

/**
 * 事件统计
 */
public interface EventStatistyContract {
    interface IView extends BaseView {

        void showListData(EventListBean listBean);

        void showEventBean(EventShowBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
