package com.sdxxtop.shuziyinan.presenter;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.sdxxtop.shuziyinan.app.App;
import com.sdxxtop.shuziyinan.app.Constants;
import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.ArticleIndexBean;
import com.sdxxtop.shuziyinan.model.bean.InitBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.bean.TrackBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.HomeContract;
import com.sdxxtop.shuziyinan.utils.SpUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends RxPresenter<HomeContract.IView> implements HomeContract.IPresenter {
    @Inject
    public HomePresenter() {
    }


    public void initApp() {
        Params params = new Params();
        params.put("pi", 1);
        Observable<RequestBean<InitBean>> observable = getEnvirApi().postAppInit(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<InitBean>() {
            @Override
            public void onSuccess(InitBean initBean) {
                mView.showInit(initBean);
            }

            @Override
            public void onFailure(int code, String error) {

            }
        });
        addSubscribe(disposable);
    }


    public void articleIndex(int position, boolean wasSelected) {
        Params params = new Params();
        Observable<RequestBean<ArticleIndexBean>> observable = getEnvirApi().postArticleIndex(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<ArticleIndexBean>() {
            @Override
            public void onSuccess(ArticleIndexBean bean) {
                if (bean != null) {
                    mView.showPermission(bean, position, wasSelected);
                } else {
                    mView.showError("没有操作权限");
                }
            }

            @Override
            public void onFailure(int code, String error) {
            }
        });
        addSubscribe(disposable);

    }

    /********** 猎鹰相关 ***********/
    public void startUploadingPoint() {
        App.getInstance().startTrackService();
        if (handler != null) {
            handler.sendEmptyMessageDelayed(0, 1000 * 60);
        }
    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            uploadingPoint();
        }
    };

    public void uploadingPoint() {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(0, 1000 * 60);
        }
        Params params = new Params();
        params.put("sid", SpUtil.getLong(Constants.SERVICE_ID, 0));
        params.put("tid", SpUtil.getLong(Constants.TERMINAL_ID, 0));
        params.put("trid", SpUtil.getLong(Constants.TRACK_ID, 0));

        Observable<RequestBean<TrackBean>> observable = getEnvirApi().postFalconReturnTime(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<TrackBean>() {
            @Override
            public void onSuccess(TrackBean bean) {
            }

            @Override
            public void onFailure(int code, String error) {
            }
        });
        addSubscribe(disposable);
    }

    @Override
    public void detachView() {
//        if (mView != null && intent != null) {
//            ((HomeActivity) mView).stopService(intent);
//        }
        if (handler != null) {
            handler.removeMessages(0);
            handler = null;
        }
        super.detachView();
    }
    /********** 猎鹰相关 ***********/
}
