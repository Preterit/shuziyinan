package com.sdxxtop.shuziyinan.presenter;


import android.content.Intent;

import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EventShowBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.EventMoveContract;
import com.sdxxtop.shuziyinan.ui.activity.EventMoveActivity;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class EventMovePresenter extends RxPresenter<EventMoveContract.IView> implements EventMoveContract.IPresenter {
    @Inject
    public EventMovePresenter() {
    }


    public void loadData() {
        Params params = new Params();
        Observable<RequestBean<EventShowBean>> observable = getEnvirApi().postEventSector(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventShowBean>() {
            @Override
            public void onSuccess(EventShowBean Bean) {
                if (mView != null) {
                    mView.showData(Bean);
                }
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }

    public void eventOperate(String extra,int partId,String eventId) {
        Params params = new Params();
        params.put("et",extra);
        params.put("pt",partId);
        params.put("ei",eventId);
        Observable<RequestBean> observable = getEnvirApi().postEventOperate(params.getData());
        Disposable disposable = RxUtils.handleHttp(observable, new IRequestCallback<RequestBean>() {
            @Override
            public void onSuccess(RequestBean Bean) {
                if (mView != null) {
                    ((EventMoveActivity)mView).showToast(Bean.getMsg());
                    Intent intent = new Intent();
                    intent.putExtra("isClose",true);
                    ((EventMoveActivity)mView).setResult(123,intent);
                    ((EventMoveActivity)mView).finish();
                }
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }
}
