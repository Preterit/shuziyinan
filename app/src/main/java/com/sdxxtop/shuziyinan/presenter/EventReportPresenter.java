package com.sdxxtop.shuziyinan.presenter;

import android.app.Activity;

import com.google.gson.internal.LinkedTreeMap;
import com.sdxxtop.shuziyinan.base.BaseActivity;
import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.EventModeBean;
import com.sdxxtop.shuziyinan.model.bean.EventSearchTitleBean;
import com.sdxxtop.shuziyinan.model.bean.EventShowBean;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.bean.ShowPartBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.ImageAndVideoParams;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.EventReportContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;
import com.sdxxtop.shuziyinan.utils.VideoCompressUtil;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class EventReportPresenter extends RxPresenter<EventReportContract.IView> implements EventReportContract.IPresenter {

    private VideoCompressUtil util;

    @Inject
    public EventReportPresenter() {
    }


    public void pushReport(String title, int pathType, int patrolType,
                           String place, String longitude, String content, List<File> imagePushPath, List<File> videoPushPath, String positionDesc) {
        ImageAndVideoParams params = new ImageAndVideoParams();
        params.put("tl", title);
        params.put("pt", pathType);
        params.put("plt", 1);
        params.put("pl", place);
        params.put("lt", longitude);
        params.put("ct", content);
        params.put("spt", positionDesc);

        params.addImagePathList("img[]", imagePushPath);
        if (videoPushPath != null && videoPushPath.size() > 0) {
            util = new VideoCompressUtil((Activity) mView);
            File file = videoPushPath.get(0);
            util.videoCompress(file.getPath());

            util.setOnVideoCompress(new VideoCompressUtil.OnVideoCompress() {
                @Override
                public void success(String path) {
                    params.addCompressVideoPath("video", new File(path));
                    request(params);
                }

                @Override
                public void fail() {
                    UIUtils.showToast("压缩失败,请重新尝试");
                }
            });
        } else {
            request(params);
        }
    }

    private void request(ImageAndVideoParams params) {
        ((BaseActivity) mView).showLoadingDialog();
        Observable<RequestBean> observable = getEnvirLongApi().postEventAdd(params.getImgAndVideoData());
        Disposable disposable = RxUtils.handleHttp(observable, new IRequestCallback<RequestBean>() {
            @Override
            public void onSuccess(RequestBean requestBean) {
                String eventId = "";
                Object data = requestBean.getData();
                if (data != null) {
                    eventId = (String) ((LinkedTreeMap) data).get("event_id");
                }
                mView.pushSuccess(eventId);
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
                mView.showError(error);
            }
        });
        addSubscribe(disposable);
    }

    public void loadAera() {
        Params params = new Params();
        Observable<RequestBean<ShowPartBean>> observable = getEnvirApi().postEventShowPart(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<ShowPartBean>() {
            @Override
            public void onSuccess(ShowPartBean showPartBean) {
//                mView.modifyRefresh();
                List<ShowPartBean.PartBean> part = showPartBean.getPart();
                if (part != null) {
                    mView.showPart(part);
                }
            }

            @Override
            public void onFailure(int code, String error) {
//                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }

    public void loadSector() {
        Params params = new Params();
        Observable<RequestBean<EventShowBean>> observable = getEnvirApi().postEventSector(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventShowBean>() {
            @Override
            public void onSuccess(EventShowBean bean) {
                if (mView != null) {
                    mView.showEventBean(bean);
                }
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }

    public void searchTitle(String title, int keywordId) {
        Params params = new Params();
        params.put("kwd", title);
        params.put("kid", keywordId);
        Observable<RequestBean<EventSearchTitleBean>> observable = getEnvirApi().postEventSearch(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventSearchTitleBean>() {
            @Override
            public void onSuccess(EventSearchTitleBean bean) {
                if (mView != null) {
                    mView.showSearchData(bean, keywordId);
                }
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }

    public void eventMode(int mSelectPartId) {
        Params params = new Params();
        params.put("pt", mSelectPartId);
        Observable<RequestBean<EventModeBean>> observable = getEnvirApi().postEventMode(params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<EventModeBean>() {
            @Override
            public void onSuccess(EventModeBean bean) {
                mView.showQuerySelect(bean);
            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }
}
