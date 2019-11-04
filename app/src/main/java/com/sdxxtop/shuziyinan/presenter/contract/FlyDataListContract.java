package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.FlyEventListBean;

import java.util.List;

/**
 * 用来copy使用的
 */
public interface FlyDataListContract {
    interface IView extends BaseView {

        void setMonthUavData(List<FlyEventListBean.MonthTash> data);

        void setDayUavList(List<FlyEventListBean.DayTash> data);

        void setSearchData(List<FlyEventListBean.DayTash> search);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
