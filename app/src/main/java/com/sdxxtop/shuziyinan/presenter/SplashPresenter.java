package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.app.Constants;
import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.AutoLoginBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.SplashContract;
import com.sdxxtop.shuziyinan.utils.SpUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SplashPresenter extends RxPresenter<SplashContract.IView> implements SplashContract.IPresenter {
    @Inject
    public SplashPresenter() {
    }

    public void autoLogin() {
        Params params = new Params();
        params.put("at", SpUtil.getString(Constants.AUTO_TOKEN));
        Observable<RequestBean<AutoLoginBean>> observable = getEnvirApi().postLoginAutoLogin(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<AutoLoginBean>() {
            @Override
            public void onSuccess(AutoLoginBean autoLoginBean) {
                handleData(autoLoginBean);
                if (mView != null) {
                    mView.autoSuccess(autoLoginBean);
                }
            }

            @Override
            public void onFailure(int code, String error) {
                if (mView != null) {
                    mView.showError(error);
                }
            }
        });
        addSubscribe(disposable);
    }

    private void handleData(AutoLoginBean autoLoginBean) {

    }
}
