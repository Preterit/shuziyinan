package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.GridreportIndexBean;

/**
 * 用来copy使用的
 */
public interface GGRContract {
    interface IView extends BaseView {
        void showData(GridreportIndexBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
