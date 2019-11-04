package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.bean.UnreadNewslistBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.DeviceCenterMsgContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class DeviceCenterMsgPresenter extends RxPresenter<DeviceCenterMsgContract.IView> implements DeviceCenterMsgContract.IPresenter {
    @Inject
    public DeviceCenterMsgPresenter() {
    }


    public void loadData(int type) {
        Params params = new Params();
        params.put("tp", type);

        Observable<RequestBean<UnreadNewslistBean>> observable = getEnvirApi().postUnreadNewslist(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<UnreadNewslistBean>() {
            @Override
            public void onSuccess(UnreadNewslistBean bean) {
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
