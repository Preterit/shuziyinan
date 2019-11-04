package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseCompanyBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.GACEContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class GACEPresenter extends RxPresenter<GACEContract.IView> implements GACEContract.IPresenter {
    @Inject
    public GACEPresenter() {
    }


    public void enterpriseCompany(int part_typeid,int parent_id) {
        Params params = new Params();
        params.put("ety",part_typeid);
        params.put("pai",parent_id);

        Observable<RequestBean<EnterpriseCompanyBean>> observable = getEnvirApi().postEnterpriseCompany(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EnterpriseCompanyBean>() {
            @Override
            public void onSuccess(EnterpriseCompanyBean bean) {
                mView.showData(bean);
            }

            @Override
            public void onFailure(int code, String error) {
            }
        });
        addSubscribe(disposable);
    }
}
