package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.FlyEventDetailBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.EventDetailContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class EventDetailPresenter extends RxPresenter<EventDetailContract.IView> implements EventDetailContract.IPresenter {
    @Inject
    public EventDetailPresenter() {
    }

    public void loadData(int eventId, String searchText) {
        Params params = new Params();
        params.put("ti", eventId);
        params.put("pl", searchText);

        Observable<RequestBean<FlyEventDetailBean>> observable = getEnvirApi().postFlyEventDetail(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<FlyEventDetailBean>() {
            @Override
            public void onSuccess(FlyEventDetailBean bean) {
                if (mView != null) {
                    mView.showData(bean);
                }
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }
}
