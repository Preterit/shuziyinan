package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.GridEventListBean;

/**
 * 用来copy使用的
 */
public interface GridEventFmContract {
    interface IView extends BaseView {

        void showData(GridEventListBean bean, int pathType, int start_page);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
