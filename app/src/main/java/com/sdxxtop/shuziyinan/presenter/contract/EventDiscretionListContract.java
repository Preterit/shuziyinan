package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.EventDiscretionListBean;

/**
 * 用来copy使用的
 */
public interface EventDiscretionListContract {
    interface IView extends BaseView {

        void showData(int size, EventDiscretionListBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
