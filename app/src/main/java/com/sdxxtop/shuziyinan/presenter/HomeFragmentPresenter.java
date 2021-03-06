package com.sdxxtop.shuziyinan.presenter;

import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.MainIndexBeanNew;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.HomeFragmentContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HomeFragmentPresenter extends RxPresenter<HomeFragmentContract.IView> implements HomeFragmentContract.IPresenter {
    @Inject
    public HomeFragmentPresenter() {
    }

    @Override
    public void loadData() {
        Params params = new Params();
        Observable<RequestBean<MainIndexBeanNew>> observable = getEnvirApi().postMainIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<MainIndexBeanNew>() {
            @Override
            public void onSuccess(MainIndexBeanNew mainIndexBean) {
                mView.showData(mainIndexBean);
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }

    @Override
    public void loadSignData() {
    }

    @Override
    public void loadInfo() {

    }
}
