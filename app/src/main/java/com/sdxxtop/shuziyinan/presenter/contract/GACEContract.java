package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseCompanyBean;

/**
 *  GridAndCompanyEventContract
 */
public interface GACEContract {
    interface IView extends BaseView {

        void showData(EnterpriseCompanyBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
