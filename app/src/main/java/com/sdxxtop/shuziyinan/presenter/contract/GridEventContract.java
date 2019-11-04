package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.GridEventCountBean;

/**
 * 用来copy使用的
 */
public interface GridEventContract {
    interface IView extends BaseView {

        void showData(GridEventCountBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
