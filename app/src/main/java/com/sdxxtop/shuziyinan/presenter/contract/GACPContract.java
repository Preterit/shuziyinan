package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.GridreportPatrolBean;

/**
 * GACP:GridAndCompanyPatrol
 */
public interface GACPContract {
    interface IView extends BaseView {

        void showData(GridreportPatrolBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
