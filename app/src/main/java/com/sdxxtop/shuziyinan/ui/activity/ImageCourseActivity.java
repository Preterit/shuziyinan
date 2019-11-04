package com.sdxxtop.shuziyinan.ui.activity;

import android.view.View;

import com.sdxxtop.shuziyinan.ui.widget.imgservice.ServiceWebActivity;

public class ImageCourseActivity extends ServiceWebActivity {

    @Override
    protected void initData() {
        initStatusBar();
        titleView.setVisibility(View.VISIBLE);
        titleView.setTitleValue("后台运行设置");
        include.setVisibility(View.VISIBLE);
        String href = getIntent().getStringExtra("href");
        webView.loadUrl("http://wap.sdxxtop.com/envir/phone/phone?name="+href);
    }
}
