package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.SectionEventBean;

/**
 * 用来copy使用的
 */
public interface UnifyEventContract {
    interface IView extends BaseView {

        void showData(SectionEventBean bean,int page_start);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
