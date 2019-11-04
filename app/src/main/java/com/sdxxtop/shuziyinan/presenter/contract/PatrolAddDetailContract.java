package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.PatrolReadBean;

/**
 * 用来copy使用的
 */
public interface PatrolAddDetailContract {
    interface IView extends BaseView {

        void showData(PatrolReadBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
