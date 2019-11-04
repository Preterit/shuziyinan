package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.MainIndexBeanNew;

public interface HomeFragmentContract {
    interface IView extends BaseView {
        void showData(MainIndexBeanNew data);
    }

    interface IPresenter extends BasePresenter<IView> {
        void loadData();

        /**
         * 加载签名的
         */
        void loadSignData();


        void loadInfo();
    }
}
