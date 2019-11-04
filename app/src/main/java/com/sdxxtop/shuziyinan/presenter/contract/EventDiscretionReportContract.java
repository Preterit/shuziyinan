package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EventSearchTitleBean;
import com.sdxxtop.shuziyinan.model.bean.PatrolAddBean;

/**
 * 用来copy使用的
 */
public interface EventDiscretionReportContract {
    interface IView extends BaseView {

        void skipDetail(PatrolAddBean bean);

        void showSearchData(EventSearchTitleBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
