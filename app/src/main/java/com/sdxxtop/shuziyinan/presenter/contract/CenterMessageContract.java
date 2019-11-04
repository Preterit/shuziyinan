package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.UnreadIndexBean;

/**
 * 用来copy使用的
 */
public interface CenterMessageContract {
    interface IView extends BaseView {

        void showData(UnreadIndexBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
