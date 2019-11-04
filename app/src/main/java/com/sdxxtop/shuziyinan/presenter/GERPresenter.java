package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EventChartBean;
import com.sdxxtop.shuziyinan.model.bean.GERPIndexBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.GERContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * GERPresenter(GrandEventReportPresenter)
 */
public class GERPresenter extends RxPresenter<GERContract.IView> implements GERContract.IPresenter {
    @Inject
    public GERPresenter() {
    }


    public void index(String startTime, String endTime) {
        Params params = new Params();
        params.put("st", startTime);
        params.put("et", endTime);

        Observable<RequestBean<GERPIndexBean>> observable = getEnvirApi().postIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<GERPIndexBean>() {
            @Override
            public void onSuccess(GERPIndexBean indexBean) {
                mView.showIndexData(indexBean);
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }

    public void eventChart(String startTime, String endTime, String chartId,boolean isAdd) {
        Params params = new Params();
        params.put("st", startTime);
        params.put("et", endTime);
        params.put("pt", chartId);

        Observable<RequestBean<EventChartBean>> observable = getEnvirApi().postEventChart(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventChartBean>() {
            @Override
            public void onSuccess(EventChartBean bean) {
                mView.showChartData(bean,chartId,isAdd);
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }
}
