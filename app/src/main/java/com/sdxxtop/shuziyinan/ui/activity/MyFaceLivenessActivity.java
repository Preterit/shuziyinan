package com.sdxxtop.shuziyinan.ui.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.baidu.idl.face.platform.ui.FaceLivenessActivity;
import com.luck.picture.lib.permissions.RxPermissions;
import com.sdxxtop.shuziyinan.R;
import com.sdxxtop.shuziyinan.presenter.FaceLivenessPresenter;
import com.sdxxtop.shuziyinan.presenter.contract.FaceLivenessContract;
import com.sdxxtop.shuziyinan.ui.widget.TitleView;
import com.sdxxtop.shuziyinan.utils.DialogUtil;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import io.reactivex.functions.Consumer;

public class MyFaceLivenessActivity extends FaceLivenessActivity implements FaceLivenessContract.IView {

    private RxPermissions mRxPermissions;

    protected FaceLivenessPresenter mPresenter;

    private DialogUtil mDialogUtil;
    private boolean mIsFace;
    private String address;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = new FaceLivenessPresenter();
            mPresenter.attachView(this);
        }

        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            mIsFace = getIntent().getBooleanExtra("isFace", true);
            address = getIntent().getStringExtra("address");
            tvLocation.setText(address);
        }

        TitleView titleView = findViewById(R.id.tv_title);
        if (!mIsFace) {
            titleView.setTitleValue("注册");
        }

        mRxPermissions = new RxPermissions(this);
        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    init();
                }
            }
        });

    }

    public void showLoadingDialog() {
        if (mDialogUtil == null) {
            mDialogUtil = new DialogUtil();
        }
        mDialogUtil.showLoadingDialog(this);
    }

    public void hideLoadingDialog() {
        if (mDialogUtil != null) {
            mDialogUtil.closeLoadingDialog(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    public void showFaceSuccess(Bitmap bitmap) {
        showLoadingDialog();

        if (!mIsFace) {
            goToRegister(bitmap);
            return;
        }

        mPresenter.loginFace(bitmap,address);
    }

    private void goToRegister(Bitmap bitmap) {
        if (bitmap ==null) {
            UIUtils.showToast("注册失败");
            return;
        }
        mPresenter.rigesterFace(bitmap);
    }

    @Override
    public void faceSuccess(String address) {
        hideLoadingDialog();

        UIUtils.showToast("打卡成功");
        tvLocation.setText(address);
        btnCard.setVisibility(View.VISIBLE);

        //打卡完成返回首页
        finish();
    }

    @Override
    public void faceRegisterSuccess(String address) {
        UIUtils.showToast("注册成功");
        setResult(100);
        finish();
    }

    @Override
    public void showError(String error) {
        UIUtils.showToast(error);
        hideLoadingDialog();
        recreate();
    }
}
