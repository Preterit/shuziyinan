package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.WorkIndexBean;

/**
 * 用来copy使用的
 */
public interface WorkFragmentContract {
    interface IView extends BaseView {

        void showIndex(WorkIndexBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
