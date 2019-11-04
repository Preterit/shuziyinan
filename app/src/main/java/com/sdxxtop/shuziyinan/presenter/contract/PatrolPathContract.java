package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseTrailBean;

/**
 * 用来copy使用的
 */
public interface PatrolPathContract {
    interface IView extends BaseView {

        void showData(EnterpriseTrailBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
