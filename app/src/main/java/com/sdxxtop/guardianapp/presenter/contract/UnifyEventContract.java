package com.sdxxtop.guardianapp.presenter.contract;

import com.sdxxtop.guardianapp.base.BasePresenter;
import com.sdxxtop.guardianapp.base.BaseView;
import com.sdxxtop.guardianapp.model.bean.SectionEventBean;

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
