package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.ExamineFinishBean;
import com.sdxxtop.shuziyinan.model.bean.StudyCheckBean;
import com.sdxxtop.shuziyinan.model.bean.StudyQuestionBean;

public interface ExamineContract {
    interface IView extends BaseView {
        void showData(StudyQuestionBean studyQuestionBean);

        void pushQuestionSuccess(StudyCheckBean studyCheckBean);

        void finishSuccess(ExamineFinishBean finishBean);

        void finishFailure();
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
