package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EventChartBean;
import com.sdxxtop.shuziyinan.model.bean.GERPIndexBean;

/**
 * 用来copy使用的
 */
public interface GERContract {
    interface IView extends BaseView {

        void showIndexData(GERPIndexBean indexBean);

        void showChartData(EventChartBean bean,String chartId,boolean isAdd);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
