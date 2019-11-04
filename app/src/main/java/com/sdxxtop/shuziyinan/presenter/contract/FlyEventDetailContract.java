package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.FlyEventDetailBean;

/**
 * 用来copy使用的
 */
public interface FlyEventDetailContract {
    interface IView extends BaseView {

        void showData(FlyEventDetailBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
