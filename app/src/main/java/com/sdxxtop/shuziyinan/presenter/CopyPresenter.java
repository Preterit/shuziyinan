package com.sdxxtop.shuziyinan.presenter;


import com.sdxxtop.shuziyinan.base.RxPresenter;
import com.sdxxtop.shuziyinan.presenter.contract.CopyContract;
import com.sdxxtop.shuziyinan.presenter.contract.HomeContract;

import javax.inject.Inject;

/**
 * 用来copy使用的
 */
public class CopyPresenter extends RxPresenter<CopyContract.IView> implements CopyContract.IPresenter {
    @Inject
    public CopyPresenter() {
    }


}
