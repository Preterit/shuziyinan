package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.ContactIndexBean;

public interface ContactContract {
    interface IView extends BaseView {
        void showList(ContactIndexBean contactIndexBean);

        void showSearchList(ContactIndexBean contactIndexBean);
    }

    interface IPresenter extends BasePresenter<ContactContract.IView> {

    }
}
