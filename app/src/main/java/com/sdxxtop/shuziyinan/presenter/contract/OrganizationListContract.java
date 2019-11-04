package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;

public interface OrganizationListContract {
    interface IView extends BaseView {
    }

    interface IPresenter extends BasePresenter<IView> {
        void loadData(int count, int type);
    }
}
