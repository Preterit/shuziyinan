package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EventModeBean;
import com.sdxxtop.shuziyinan.model.bean.EventSearchTitleBean;
import com.sdxxtop.shuziyinan.model.bean.EventShowBean;
import com.sdxxtop.shuziyinan.model.bean.ShowPartBean;

import java.util.List;

public interface EventReportContract {
    interface IView extends BaseView {
        void pushSuccess(String eventId);

        void showPart(List<ShowPartBean.PartBean> par);

        void showSearchData(EventSearchTitleBean bean,int keywordId);

        void showQuerySelect(EventModeBean bean);

        void showEventBean(EventShowBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
