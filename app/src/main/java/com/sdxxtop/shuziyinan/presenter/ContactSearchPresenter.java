package com.sdxxtop.shuziyinan.presenter;

import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.ContactIndexBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.ContactContract;
import com.sdxxtop.shuziyinan.presenter.contract.ContactSearchContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ContactSearchPresenter extends RxPresenter<ContactSearchContract.IView> implements ContactSearchContract.IPresenter {
    @Inject
    public ContactSearchPresenter() {
    }

    public void loadData(String key) {
        Params params = new Params();
        params.put("sh", key);
        Observable<RequestBean<ContactIndexBean>> observable = getEnvirApi().postContactSearch(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<ContactIndexBean>() {
            @Override
            public void onSuccess(ContactIndexBean contactIndexBean) {
                List<ContactIndexBean.ContactBean> user = contactIndexBean.getUser();
                mView.showList(user);
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }

}
