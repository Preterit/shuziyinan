package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseIndexBean;

/**
 * GrantCompanyReportContract
 */
public interface GCRContract {
    interface IView extends BaseView {

        void showData(EnterpriseIndexBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
