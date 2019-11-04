package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.LoginBean;

public interface LoginContract {
    interface IView extends BaseView {
        void loginSuccess(LoginBean loginBean);
        void sendCodeSuccess();
        void sendCodeError();
    }

    interface IPresenter extends BasePresenter<LoginContract.IView> {
        void login(String mobile, String authCode, String partId);
    }
}
