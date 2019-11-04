package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;

/**
 * 用来copy使用的
 */
public interface FaceLivenessContract {
    interface IView extends BaseView {
        void faceSuccess(String address);
        void faceRegisterSuccess(String address);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
