package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseSecurityBean;
import com.sdxxtop.shuziyinan.model.bean.GridreportOperatorBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.SafeStaffDetailContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class SafeStaffDetailPresenter extends RxPresenter<SafeStaffDetailContract.IView> implements SafeStaffDetailContract.IPresenter {

    @Inject
    public SafeStaffDetailPresenter() {

    }

    public void enterpriseSecurity(int id, String startTime, String endTime) {
        Params params = new Params();
        params.put("pai", id);
        params.put("st", startTime);
        params.put("et", endTime);

        Observable<RequestBean<EnterpriseSecurityBean>> observable = getEnvirApi().postEnterpriseSecurity(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EnterpriseSecurityBean>() {
            @Override
            public void onSuccess(EnterpriseSecurityBean bean) {
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

    public void gridreportOperator(int id, String startTime, String endTime) {
        Params params = new Params();
        params.put("puid", id);
        params.put("st", startTime);
        params.put("et", endTime);

        Observable<RequestBean<GridreportOperatorBean>> observable = getEnvirApi().postGridreportOperator(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<GridreportOperatorBean>() {
            @Override
            public void onSuccess(GridreportOperatorBean bean) {
                if (bean!=null&&mView!=null){
                    mView.showGridData(bean);
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
