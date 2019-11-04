package com.sdxxtop.shuziyinan.presenter;

import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.bean.SignLogBean;
import com.sdxxtop.shuziyinan.model.bean.TrackPointBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.PatrolContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class PatrolPresenter extends RxPresenter<PatrolContract.IView> implements PatrolContract.IPresenter {
    @Inject
    public PatrolPresenter() {
    }


    public void loadData(String date,int type) {
        Params params = new Params();
        params.put("sd", date);
        params.put("tp", type);
        Observable<RequestBean<SignLogBean>> observable = getEnvirApi().postMainSignLog(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<SignLogBean>() {
            @Override
            public void onSuccess(SignLogBean signLogBean) {
                if (mView != null) {
                    mView.showData(signLogBean);
                }
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }

    public void loadTrack(String date,int type) {
        Params params = new Params();
        params.put("sd", date);
        params.put("tp", type);
        Observable<RequestBean<TrackPointBean>> observable = getEnvirApi().postMoreTrack(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<TrackPointBean>() {
            @Override
            public void onSuccess(TrackPointBean bean) {
                if (mView != null) {
                    mView.showTrackData(bean);
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
