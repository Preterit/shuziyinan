package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EventDiscretionListBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.EventDiscretionListContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class EventDiscretionListPresenter extends RxPresenter<EventDiscretionListContract.IView> implements EventDiscretionListContract.IPresenter {
    @Inject
    public EventDiscretionListPresenter() {
    }


    public void loadData(int size) {
        Params params = new Params();
        params.put("sp", size);
        Observable<RequestBean<EventDiscretionListBean>> observable = getEnvirApi().postPatrolIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventDiscretionListBean>() {
            @Override
            public void onSuccess(EventDiscretionListBean bean) {
                mView.showData(size,bean);
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }
}
