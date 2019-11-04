package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.UcenterIndexBean;

/**
 * 用来copy使用的
 */
public interface MineContract {
    interface IView extends BaseView {
        void showList(UcenterIndexBean ucenterIndexBean);

        void changeIconSuccess();
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
