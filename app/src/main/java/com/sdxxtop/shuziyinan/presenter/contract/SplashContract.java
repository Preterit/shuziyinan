package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.AutoLoginBean;

public interface SplashContract {
    interface IView extends BaseView {
        void autoSuccess(AutoLoginBean autoLoginBean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
