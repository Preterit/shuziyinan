package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;

/**
 * 用来copy使用的
 */
public interface ReCheckContract {
    interface IView extends BaseView {

        void showMsg(RequestBean bean);

    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
