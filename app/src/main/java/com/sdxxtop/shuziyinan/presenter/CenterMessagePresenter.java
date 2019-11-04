package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.bean.UnreadIndexBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.CenterMessageContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class CenterMessagePresenter extends RxPresenter<CenterMessageContract.IView> implements CenterMessageContract.IPresenter {
    @Inject
    public CenterMessagePresenter() {
    }


    public void unreadIndex() {
        Params params = new Params();
        params.put("plid", 1);
        Observable<RequestBean<UnreadIndexBean>> observable = getEnvirApi().postUnreadIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<UnreadIndexBean>() {
            @Override
            public void onSuccess(UnreadIndexBean bean) {
                mView.showData(bean);
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }
}
