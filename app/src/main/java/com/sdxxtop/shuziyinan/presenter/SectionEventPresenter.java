package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.presenter.contract.SectionEventContract;

import javax.inject.Inject;

/**
 * 用来copy使用的
 */
public class SectionEventPresenter extends RxPresenter<SectionEventContract.IView> implements SectionEventContract.IPresenter {
    @Inject
    public SectionEventPresenter() {
    }


}
