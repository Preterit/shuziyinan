package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.GridreportIndexBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.GGRContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * GrantGridReportPresenter
 */
public class GGRPresenter extends RxPresenter<GGRContract.IView> implements GGRContract.IPresenter {
    @Inject
    public GGRPresenter() {
    }


    public void gridreportIndex(int part_typeid) {
        Params params = new Params();
        params.put("ety", part_typeid);

        Observable<RequestBean<GridreportIndexBean>> observable = getEnvirApi().postGridreportIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<GridreportIndexBean>() {
            @Override
            public void onSuccess(GridreportIndexBean bean) {
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
