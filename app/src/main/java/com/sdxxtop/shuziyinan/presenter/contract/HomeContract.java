package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.ArticleIndexBean;
import com.sdxxtop.shuziyinan.model.bean.InitBean;

public interface HomeContract {
    interface IView extends BaseView {
        void showInit(InitBean initBean);

        void showPermission(ArticleIndexBean bean,int position,boolean wasSelected);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
