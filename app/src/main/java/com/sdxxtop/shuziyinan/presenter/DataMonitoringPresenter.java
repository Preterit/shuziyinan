package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.AuthDataBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.DataMonitoringContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class DataMonitoringPresenter extends RxPresenter<DataMonitoringContract.IView> implements DataMonitoringContract.IPresenter {
    @Inject
    public DataMonitoringPresenter() {
    }


    public void loadData() {
        Params params = new Params();
        params.put("plid", 1);
        Observable<RequestBean<AuthDataBean>> observable = getEnvirApi().posDeviceIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<AuthDataBean>() {
            @Override
            public void onSuccess(AuthDataBean bean) {
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
