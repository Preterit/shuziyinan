package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.PatrolReadBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.PatrolAddDetailContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class PatrolAddDetailPresenter extends RxPresenter<PatrolAddDetailContract.IView> implements PatrolAddDetailContract.IPresenter {
    @Inject
    public PatrolAddDetailPresenter() {
    }


    public void patrolRead(int patrol_id) {
        Params params = new Params();
        params.put("pld", patrol_id);
        Observable<RequestBean<PatrolReadBean>> observable = getEnvirApi().postPatrolRead(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<PatrolReadBean>() {
            @Override
            public void onSuccess(PatrolReadBean bean) {
                mView.showData(bean);
            }

            @Override
            public void onFailure(int code, String error) {
                mView.showError(error);
            }
        });
        addSubscribe(disposable);
    }
}
