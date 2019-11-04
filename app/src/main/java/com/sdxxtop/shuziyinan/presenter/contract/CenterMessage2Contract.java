package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.UnreadNewslistBean;

/**
 * 用来copy使用的
 */
public interface CenterMessage2Contract {
    interface IView extends BaseView {

        void showData(UnreadNewslistBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
