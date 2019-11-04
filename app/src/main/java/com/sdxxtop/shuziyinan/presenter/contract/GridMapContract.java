package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.MainMapBean;

public interface GridMapContract {
    interface IView extends BaseView {
        void showMap(MainMapBean mainMapBean);

    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
