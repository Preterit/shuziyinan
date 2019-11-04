package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseIndexBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.GCRContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * GrantCompanyReportPresenter
 */
public class GCRPresenter extends RxPresenter<GCRContract.IView> implements GCRContract.IPresenter {
    @Inject
    public GCRPresenter() {
    }


    public void enterpriseIndex(int part_typeid) {
        Params params = new Params();
        params.put("ety", part_typeid);

        Observable<RequestBean<EnterpriseIndexBean>> observable = getEnvirApi().postEnterpriseIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EnterpriseIndexBean>() {
            @Override
            public void onSuccess(EnterpriseIndexBean bean) {
                if (mView != null) {
                    mView.showData(bean);
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
