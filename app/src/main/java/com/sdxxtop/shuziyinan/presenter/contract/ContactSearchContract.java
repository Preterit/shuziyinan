package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.ContactIndexBean;

import java.util.List;

/**
 * 用来copy使用的
 */
public interface ContactSearchContract {
    interface IView extends BaseView {
        void showList(List<ContactIndexBean.ContactBean> contactBean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
