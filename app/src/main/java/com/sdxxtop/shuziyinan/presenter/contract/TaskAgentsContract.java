package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EventIndexBean;

public interface TaskAgentsContract {
    interface IView extends BaseView {
        void showData(int page, EventIndexBean eventIndexBean);
    }

    interface IPresenter extends BasePresenter<IView> {

        void loadData(int page, int type);
    }
}
