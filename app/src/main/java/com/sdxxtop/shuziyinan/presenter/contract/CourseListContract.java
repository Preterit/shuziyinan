package com.sdxxtop.shuziyinan.presenter.contract;

import com.sdxxtop.shuziyinan.base.BasePresenter;
import com.sdxxtop.shuziyinan.base.BaseView;
import com.sdxxtop.shuziyinan.model.bean.StudyCourseBean;
import com.sdxxtop.shuziyinan.model.bean.course.CourseCellBean;
import com.sdxxtop.shuziyinan.model.bean.course.ExamCellBean;

import java.util.List;

public interface CourseListContract {
    interface IView extends BaseView {
        void showList(List<CourseCellBean> studyCourseBeans);
        void showExam(List<ExamCellBean> examCellBeans);
    }

    interface IPresenter extends BasePresenter<IView> {

    }
}
