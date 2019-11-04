package com.sdxxtop.shuziyinan.presenter;

import com.orhanobut.logger.Logger;
import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.net.RetrofitHelper;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.NewDetailContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author :  lwb
 * Date: 2019/3/18
 * Desc:
 */
public class NewsDetailPresenter extends RxPresenter<NewDetailContract.IView> implements NewDetailContract.IPresenter {
    @Inject
    public NewsDetailPresenter() {
    }

    public void loadData(int article_id) {
        Params params = new Params();
        params.put("ai", article_id);
        Disposable subscribe = RetrofitHelper.getNewsApi().getAllArticleInfo(params.getData())
                .compose(RxUtils.schedulers())
//                .compose(RxUtils.handleResult())
                .subscribe(new Consumer<RequestBean>() {
                    @Override
                    public void accept(RequestBean requestBean) throws Exception {
                        if (requestBean != null) {
                            if (requestBean.getCode()==200){
                                UIUtils.showToast(requestBean.getMsg());
                            }else{
                                UIUtils.showToast(requestBean.getMsg());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e( "--- " + throwable.toString());
                    }
                });
        addSubscribe(subscribe);
    }
}
