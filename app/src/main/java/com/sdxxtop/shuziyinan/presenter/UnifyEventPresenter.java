package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.bean.SectionEventBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.UnifyEventContract;
import com.sdxxtop.shuziyinan.ui.fragment.SectionEventFragment;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 用来copy使用的
 */
public class UnifyEventPresenter extends RxPresenter<UnifyEventContract.IView> implements UnifyEventContract.IPresenter {
    @Inject
    public UnifyEventPresenter() {
    }


    public void loadData(int type,int start_page) {
        ((SectionEventFragment)mView).showLoadingDialog();
        Params params = new Params();
        params.put("ty", type);
        params.put("sp", start_page);
        Observable<RequestBean<SectionEventBean>> observable = getEnvirApi().postSectionEvent(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<SectionEventBean>() {
            @Override
            public void onSuccess(SectionEventBean bean) {
                ((SectionEventFragment)mView).closeLoadingDialog();
                if (mView!=null&&bean!=null){
                    mView.showData(bean,start_page);
                }
            }

            @Override
            public void onFailure(int code, String error) {
                ((SectionEventFragment)mView).closeLoadingDialog();
                mView.showError(error);
            }
        });
        addSubscribe(disposable);
    }
}
