package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.DeviceMapBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.MonitorMapContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class MonitorMapPresenter extends RxPresenter<MonitorMapContract.IView> implements MonitorMapContract.IPresenter {
    @Inject
    public MonitorMapPresenter() {
    }


    public void loadData() {
        Params params = new Params();
        Observable<RequestBean<DeviceMapBean>> observable = getEnvirApi().postDeviceDeviceMap(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<DeviceMapBean>() {
            @Override
            public void onSuccess(DeviceMapBean bean) {
                if (mView != null) {
                    mView.showMapInfo(bean);
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
}
