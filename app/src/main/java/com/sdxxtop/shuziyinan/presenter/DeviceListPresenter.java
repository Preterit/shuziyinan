package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.DeviceListBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.DeviceListContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class DeviceListPresenter extends RxPresenter<DeviceListContract.IView> implements DeviceListContract.IPresenter {
    @Inject
    public DeviceListPresenter() {
    }


    public void loadData(int part_typeid, int type) {
        Params params = new Params();
        params.put("ty", type);
        params.put("ety", part_typeid);

        Observable<RequestBean<DeviceListBean>> observable = getEnvirApi().postDeviceDeviceList(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<DeviceListBean>() {
            @Override
            public void onSuccess(DeviceListBean bean) {
                if (mView != null) {
                    mView.showData(bean);
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
