package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseSecurityBean;
import com.sdxxtop.shuziyinan.model.bean.GridreportOperatorBean;

/**
 * 用来copy使用的
 */
public interface SafeStaffDetailContract {
    interface IView extends BaseView {

        void showData(EnterpriseSecurityBean bean);

        void showGridData(GridreportOperatorBean bean);

    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
