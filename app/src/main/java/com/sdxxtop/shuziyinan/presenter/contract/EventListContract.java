package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.AllarticleBean;

public interface EventListContract {
    interface IView extends BaseView {
        void showData(AllarticleBean data);
    }

    interface IPresenter extends BasePresenter<IView> {
        void loadData(int count, int type);
    }
}
