package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EventShowBean;

/**
 * 用来copy使用的
 */
public interface EventMoveContract {
    interface IView extends BaseView {

        void showData(EventShowBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
