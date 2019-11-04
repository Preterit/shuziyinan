package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseTrailBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.PatrolPathContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class PatrolPathPresenter extends RxPresenter<PatrolPathContract.IView> implements PatrolPathContract.IPresenter {
    @Inject
    public PatrolPathPresenter() {
    }


    public void enterpriseTrail(String userid,String start_time,int reportType) {
        Params params = new Params();
        params.put("puid", userid);
        params.put("st", start_time);
        Observable<RequestBean<EnterpriseTrailBean>> observable = null;
        if (reportType==1){ // 网格员
            observable = getEnvirApi().postGridreportTrail(params.getData());
        }else if (reportType==2){  // 企业
            observable = getEnvirApi().postEnterpriseTrail(params.getData());
        }

        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EnterpriseTrailBean>() {
            @Override
            public void onSuccess(EnterpriseTrailBean bean) {
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
