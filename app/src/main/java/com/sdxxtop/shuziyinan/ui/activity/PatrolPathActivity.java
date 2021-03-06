package com.sdxxtop.shuziyinan.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.luck.picture.lib.permissions.RxPermissions;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.sdxxtop.shuziyinan.R;
import com.sdxxtop.shuziyinan.base.BaseMvpActivity;
import com.sdxxtop.shuziyinan.model.bean.EnterpriseTrailBean;
import com.sdxxtop.shuziyinan.presenter.PatrolPathPresenter;
import com.sdxxtop.shuziyinan.presenter.contract.PatrolPathContract;
import com.sdxxtop.shuziyinan.ui.pop.StatSelectionDateWindow;
import com.sdxxtop.shuziyinan.ui.widget.TabTextView;
import com.sdxxtop.shuziyinan.ui.widget.TitleView;
import com.sdxxtop.shuziyinan.utils.Date2Util;
import com.sdxxtop.shuziyinan.utils.DateUtil;
import com.sdxxtop.shuziyinan.utils.LocationUtil;
import com.sdxxtop.shuziyinan.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

import static com.sdxxtop.shuziyinan.utils.MarkerImgLoad.convertViewToBitmap;

public class PatrolPathActivity extends BaseMvpActivity<PatrolPathPresenter> implements PatrolPathContract.IView, AMap.OnMapLoadedListener {

    @BindView(R.id.title)
    TitleView title;
    @BindView(R.id.map_view)
    MapView mMapView;
    @BindView(R.id.ttv_1)
    TabTextView ttv1;
    @BindView(R.id.ttv_2)
    TabTextView ttv2;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    private String chooseDay;

    private AMap aMap;
    private List<LatLng> list = new ArrayList<>();
    private String userid;
    private int reportType;  // 网格员:1  / 企业 :2
    private String name;
    private StatSelectionDateWindow selectionDateWindow;
    private RxPermissions mRxPermissions;
    private PolylineOptions polylineOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_patrol_path;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showError(String error) {
        ttv1.setValue("--", "巡逻总距离(km)");
        ttv2.setValue("--", "巡逻总时长(h)");
        if (aMap != null) {
            aMap.clear();
        }
        UIUtils.showToast(error);
    }

    @Override
    protected void initView() {
        super.initView();
        name = getIntent().getStringExtra("name");
        userid = getIntent().getStringExtra("userid");
        reportType = getIntent().getIntExtra("reportType", -1);
        title.setTitleValue(name + "巡逻报告");

        ttv1.setValue("--", "巡逻总距离(km)");
        ttv2.setValue("--", "巡逻总时长(h)");
        ttv2.tvLine.setVisibility(View.GONE);
        ttv1.tvLine.setVisibility(View.GONE);

        mRxPermissions = new RxPermissions(this);
        mRxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    initMap();
                    initLocation();
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        showLoadingDialog();
        mPresenter.enterpriseTrail(userid, "", reportType);
    }

    /**
     * 初始化AMap对象
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.setOnMapLoadedListener(this);
            polylineOptions = new PolylineOptions().color(Color.parseColor("#32B16C")).width(7).setDottedLine(false).geodesic(false);
        }
    }

    /**
     * 地图加载完成
     */
    @Override
    public void onMapLoaded() {
    }

    /**
     * 定位
     */
    private void initLocation() {
        LocationUtil locationUtil = new LocationUtil();
        locationUtil.startLocate(this);
        locationUtil.setLocationCallBack(new LocationUtil.ILocationCallBack() {
            @Override
            public void callBack(String str, double lat, double lgt, AMapLocation amapLocation) {
                if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                    locationUtil.stopLocation();
                    LatLng curLatlng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLatlng, 16f));
                } else {
                    String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                    Log.e("AmapErr", errText);
                }
            }
        });
    }

    /**
     * 画线
     *
     * @param data
     */
    private void setUpMap(EnterpriseTrailBean.TrailInfo data) {
        //起点位置和  地图界面大小控制
//        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(list.get(0), 7));
        aMap.setMapTextZIndex(2);
        // 绘制一条带有纹理的直线
        //添加到地图
        if (polylineOptions!=null){
            polylineOptions.add(data.getLatLng());
        }

//        LatLngBounds bounds = getLatLngBounds();
//        CameraUpdateFactory.newLatLngBounds(bounds, 30);
    }

    private BitmapDescriptor customizeMarkerIconLastAndFirst(EnterpriseTrailBean.TrailInfo trailInfo, String str) {
        final View markerView = LayoutInflater.from(mContext).inflate(R.layout.view_map_with_time, null);
        TextView time = markerView.findViewById(R.id.tv_time);
        TextView tv_title = markerView.findViewById(R.id.tv_title);
        String signTime = trailInfo.getSign_time();
        time.setText(DateUtil.getFormatMapTime(signTime));
        tv_title.setText(str);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(markerView));
        if (bitmapDescriptor != null) {
            return bitmapDescriptor;
        } else {
            return BitmapDescriptorFactory.fromResource(R.mipmap.ic_success);
        }
    }

    private BitmapDescriptor customizeMarkerIcon(EnterpriseTrailBean.TrailInfo trailInfo) {
        final View markerView = LayoutInflater.from(mContext).inflate(R.layout.view_with_time, null);
        TextView time = markerView.findViewById(R.id.tv_time);
        String signTime = trailInfo.getSign_time();
        time.setText(DateUtil.getFormatMapTime(signTime));
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(markerView));
        if (bitmapDescriptor != null) {
            return bitmapDescriptor;
        } else {
            return BitmapDescriptorFactory.fromResource(R.mipmap.ic_success);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mapView.onDestroy()，销毁地图
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    private LatLngBounds getLatLngBounds() {
        LatLngBounds.Builder b = LatLngBounds.builder();
        for (int i = 0; i < list.size(); i++) {
            b.include(list.get(i));
        }
        return b.build();
    }

    @OnClick({R.id.ttv_1, R.id.ttv_2, R.id.tv_end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ttv_1:
            case R.id.ttv_2:
                Intent intent = new Intent(this, SafeStaffDetailActivity.class);
                intent.putExtra("id", Integer.parseInt(userid));
                intent.putExtra("type", reportType);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
            case R.id.tv_end_time: // 选择时间
                showSelectDateWindow();
                break;
        }
    }

    @Override
    public void showData(EnterpriseTrailBean bean) {
        aMap.clear();
        list.clear();
        hideLoadingDialog();
        ttv1.setValue(String.valueOf(bean.getDistance()), "巡逻总距离(km)");
        ttv2.setValue(String.valueOf(bean.getTotal_time()), "巡逻总时长(h)");
        List<EnterpriseTrailBean.TrailInfo> trail_info = bean.getTrail_info();

        AsyncTask.THREAD_POOL_EXECUTOR.execute(new MyRunnable(trail_info));
    }

    class MyRunnable implements Runnable {
        private List<EnterpriseTrailBean.TrailInfo> mData;

        public MyRunnable(List<EnterpriseTrailBean.TrailInfo> mData) {
            this.mData = mData;
        }

        @Override
        public void run() {
            if (mData != null && mData.size() > 0) {
                for (EnterpriseTrailBean.TrailInfo trailInfo : mData) {
                    setUpMap(trailInfo);
                }

                if (mData.size() > 1) {
                    //起点图标
                    aMap.addMarker(new MarkerOptions()
                            .position(mData.get(0).getLatLng())
                            .icon(customizeMarkerIconLastAndFirst(mData.get(0), "起点")));

                    //终点坐标
                    if (mData.size() > 0) {
                        aMap.addMarker(new MarkerOptions()
                                .position(mData.get(mData.size() - 1).getLatLng())
                                .icon(customizeMarkerIconLastAndFirst(mData.get(mData.size() - 1), "终点")));
                    }
                } else if (mData.size() == 1) {
                    //起点图标
                    aMap.addMarker(new MarkerOptions()
                            .position(mData.get(0).getLatLng())
                            .icon(customizeMarkerIconLastAndFirst(mData.get(0), "起点")));
                }


                if (mData != null && mData.size() > 0) {
                    moveMapToPosition(mData.get(0).getLatLng());
                }
                mMapView.getMap().addPolyline(polylineOptions);
            }
        }
    }

    /**
     * 选择日期的pop
     */
    private void showSelectDateWindow() {
        if (selectionDateWindow == null) {
            selectionDateWindow = new StatSelectionDateWindow(this, false, false, true);

            selectionDateWindow.setSelectorDateListener(new StatSelectionDateWindow.SelectorDateListener() {
                @Override
                public void onSelector(String date, CalendarDay calendarDay) {
                    // 请求网络
                    chooseDay = date;
                    tvEndTime.setText(getFormatDate(chooseDay));
                    mPresenter.enterpriseTrail(userid, date, reportType);
                    selectionDateWindow.dismiss();
                }
            });

            selectionDateWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                }
            });
        }
        selectionDateWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_patrol_path, null), Gravity.BOTTOM, 0, 0);
    }

    /**
     * by moos on 2018/01/12
     * func:移动地图视角到某个精确位置
     *
     * @param latLng 坐标
     */
    private void moveMapToPosition(LatLng latLng) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(
                new CameraPosition(
                        latLng,//新的中心点坐标
                        16,    //新的缩放级别
                        0,     //俯仰角0°~45°（垂直与地图时为0）
                        0      //偏航角 0~360° (正北方为0)
                ));
        aMap.animateCamera(cameraUpdate, 300, new AMap.CancelableCallback() {
            @Override
            public void onFinish() {
            }

            @Override
            public void onCancel() {
            }
        });
    }

    public String getFormatDate(String sDate) {
        if (sDate.equals(Date2Util.getDate())) {
            return "今日";
        }
        String formatDate = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            formatDate = sdf2.format(sdf1.parse(sDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatDate;
    }
}
