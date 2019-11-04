package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.BaseMvpActivity;
import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EventListBean;
import com.sdxxtop.shuziyinan.model.bean.EventShowBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.EventStatistyContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 事件统计
 */
public class EventStatistyPresenter extends RxPresenter<EventStatistyContract.IView> implements EventStatistyContract.IPresenter {
    @Inject
    public EventStatistyPresenter() {
    }


    public void eventlist(int event_type, int part_typeid, String startTime, String endTime) {
        Params params = new Params();
        params.put("pt", part_typeid);
        params.put("st", startTime);
        params.put("et", endTime);
        params.put("ety", event_type);

        Observable<RequestBean<EventListBean>> observable = getEnvirApi().postEventlist(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventListBean>() {
            @Override
            public void onSuccess(EventListBean listBean) {
                if (mView != null) {
                    mView.showListData(listBean);
                }
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }

    public void eventShow() {
        Params params = new Params();

        Observable<RequestBean<EventShowBean>> observable = getEnvirApi().postEventreportShow(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventShowBean>() {
            @Override
            public void onSuccess(EventShowBean bean) {
                if (mView != null) {
                    mView.showEventBean(bean);
                }
            }

            @Override
            public void onFailure(int code, String error) {
                if (mView != null) {
                    ((BaseMvpActivity) mView).showToast(error);
                }
            }
        });
        addSubscribe(disposable);
    }
}
