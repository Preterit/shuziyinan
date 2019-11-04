package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EventIndexBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.TaskAgentsContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class TaskAgentsPresenter extends RxPresenter<TaskAgentsContract.IView> implements TaskAgentsContract.IPresenter {
    @Inject
    public TaskAgentsPresenter() {
    }


    @Override
    public void loadData(int page, int type /* 1.事件上报，2.我的事件*/) {
        Params params = new Params();
        params.put("et", type);
        params.put("plid", 1);
        Observable<RequestBean<EventIndexBean>> observable = getEnvirApi().postEventIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventIndexBean>() {
            @Override
            public void onSuccess(EventIndexBean eventIndexBean) {
                mView.showData(page, eventIndexBean);
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }
}
