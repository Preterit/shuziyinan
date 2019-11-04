package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.bean.UnreadNewslistBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.CenterMessage2Contract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class CenterMessage2Presenter extends RxPresenter<CenterMessage2Contract.IView> implements CenterMessage2Contract.IPresenter {
    @Inject
    public CenterMessage2Presenter() {
    }


    public void unreadNewslist(int type) {
        Params params = new Params();
        params.put("tp",type);
        Observable<RequestBean<UnreadNewslistBean>> observable = getEnvirApi().postUnreadNewslist(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<UnreadNewslistBean>() {
            @Override
            public void onSuccess(UnreadNewslistBean bean) {
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
