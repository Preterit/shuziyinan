package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.PartEventListBean;

/**
 * PEL:PartEventList简写
 */
public interface PELContract {
    interface IView extends BaseView {

        void showData(PartEventListBean bean,int start_page);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
