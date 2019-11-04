package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseUserdetailsBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.SafeStaffDetail2Contract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class SafeStaffDetail2Presenter extends RxPresenter<SafeStaffDetail2Contract.IView> implements SafeStaffDetail2Contract.IPresenter {
    @Inject
    public SafeStaffDetail2Presenter() {
    }


    public void enterpriseUserdetails(int partId,String startTime,String endTime) {
        Params params = new Params();
        params.put("pai", partId);
        params.put("st", startTime);
        params.put("et", endTime);

        Observable<RequestBean<EnterpriseUserdetailsBean>> observable = getEnvirApi().postEnterpriseUserdetails(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EnterpriseUserdetailsBean>() {
            @Override
            public void onSuccess(EnterpriseUserdetailsBean bean) {
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
