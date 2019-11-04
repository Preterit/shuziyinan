package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.FlyEventListBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.FlyDataListContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class FlyDataListPresenter extends RxPresenter<FlyDataListContract.IView> implements FlyDataListContract.IPresenter {
    @Inject
    public FlyDataListPresenter() {
    }


    public void getUavEventData(String data) {
        Params params = new Params();
        params.put("st",data);

        Observable<RequestBean<FlyEventListBean>> observable = getEnvirApi().postFlyEventList(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<FlyEventListBean>() {
            @Override
            public void onSuccess(FlyEventListBean bean) {
                if (mView != null) {
                    if (bean.day_tash != null) {
                        mView.setDayUavList(bean.day_tash);
                    }
                }
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }

    public void getUavData(String month) {
        Params params = new Params();
        params.put("mt",month);

        Observable<RequestBean<FlyEventListBean>> observable = getEnvirApi().postMonthFlyEvent(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<FlyEventListBean>() {
            @Override
            public void onSuccess(FlyEventListBean bean) {
                if (mView != null) {
                    if (bean.month_uav != null) {
                        mView.setMonthUavData(bean.month_uav);
                    }
                }
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }

    public void getSearchData(String tx) {
        Params params = new Params();
        params.put("task",tx);

        Observable<RequestBean<FlyEventListBean>> observable = getEnvirApi().postSearchEvent(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<FlyEventListBean>() {
            @Override
            public void onSuccess(FlyEventListBean bean) {
                if (mView != null) {
                    if (bean.search != null) {
                        mView.setSearchData(bean.search);
                    }
                }
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }
}
