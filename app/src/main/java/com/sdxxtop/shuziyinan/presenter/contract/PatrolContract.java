package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.SignLogBean;
import com.sdxxtop.shuziyinan.model.bean.TrackPointBean;

public interface PatrolContract {
    interface IView extends BaseView {
        void showData(SignLogBean signLogBean);

        void showTrackData(TrackPointBean bean);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
