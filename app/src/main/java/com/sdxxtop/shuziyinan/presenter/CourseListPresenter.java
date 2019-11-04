package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.model.bean.RequestBean;
import com.sdxxtop.shuziyinan.model.bean.StudyCourseBean;
import com.sdxxtop.shuziyinan.model.bean.course.CourseCellBean;
import com.sdxxtop.shuziyinan.model.bean.course.ExamCellBean;
import com.sdxxtop.shuziyinan.model.http.callback.IRequestCallback;
import com.sdxxtop.shuziyinan.model.http.net.Params;
import com.sdxxtop.shuziyinan.model.http.util.RxUtils;
import com.sdxxtop.shuziyinan.presenter.contract.CourseListContract;
import com.sdxxtop.shuziyinan.presenter.contract.HomeContract;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class CourseListPresenter extends RxPresenter<CourseListContract.IView> implements CourseListContract.IPresenter {
    @Inject
    public CourseListPresenter() {
    }

    public void loadData(String name) {
        Params params = new Params();
        Observable<RequestBean<StudyCourseBean>> observable = getEnvirApi().postStudyCourse(name, params.getData());
        Disposable disposable = RxUtils.handleDataHttp(observable, new IRequestCallback<StudyCourseBean>() {
            @Override
            public void onSuccess(StudyCourseBean studyCourseBean) {

                if ("exam".equals(name)) {
                    List<ExamCellBean> exam = studyCourseBean.getExam();
                    if (exam != null) {
                        mView.showExam(exam);
                    }
                } else {
                    List<CourseCellBean> course = studyCourseBean.getCourse();
                    if (course != null) {
                        mView.showList(course);
                    }
                }

            }

            @Override
            public void onFailure(int code, String error) {
                UIUtils.showToast(error);
            }
        });
        addSubscribe(disposable);
    }
}
