package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.GridreportUserreportBean;

/**
 * 用来copy使用的
 */
public interface GridreportUserreportContract {
    interface IView extends BaseView {

        void showData(GridreportUserreportBean bean,int start_page);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
