package com.sdxxtop.shuziyinan.presenter;

import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.AllarticleBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.EventListContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class EventListPresenter extends RxPresenter<EventListContract.IView> implements EventListContract.IPresenter {
    @Inject
    public EventListPresenter() {
    }

    @Override
    public void loadData(int count, int type) {
        Params params = new Params();
        params.put("an", count);
        params.put("te", type);

        Observable<RequestBean<AllarticleBean>> observable = getEnvirApi().postAllarticleData(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<AllarticleBean>() {
            @Override
            public void onSuccess(AllarticleBean mainIndexBean) {
                if (mView != null) {
                    mView.showData(mainIndexBean);
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
