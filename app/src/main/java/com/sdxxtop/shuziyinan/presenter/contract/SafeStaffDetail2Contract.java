package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseUserdetailsBean;

/**
 * 用来copy使用的
 */
public interface SafeStaffDetail2Contract {
    interface IView extends BaseView {

        void showData(EnterpriseUserdetailsBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
