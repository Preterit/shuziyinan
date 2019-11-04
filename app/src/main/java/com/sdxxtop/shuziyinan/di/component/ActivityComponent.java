package com.sdxxtop.shuziyinan.di.component;

import android.app.Activity;

import com.sdxxtop.shuziyinan.di.module.ActivityModule;
import com.sdxxtop.shuziyinan.di.qualifier.ActivityScope;
import com.sdxxtop.shuziyinan.ui.activity.CenterMessage2Activity;
import com.sdxxtop.shuziyinan.ui.activity.CenterMessageActivity;
import com.sdxxtop.shuziyinan.ui.activity.ContactActivity;
import com.sdxxtop.shuziyinan.ui.activity.ContactSearchActivity;
import com.sdxxtop.shuziyinan.ui.activity.DeviceCenterMsgActivity;
import com.sdxxtop.shuziyinan.ui.activity.DeviceDataDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.DeviceListActivity;
import com.sdxxtop.shuziyinan.ui.activity.DeviceWarnDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventDiscretionListActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventDiscretionReportActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventMoveActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventReportActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventReportDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventReportDetailSecondActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventReportListActivity;
import com.sdxxtop.shuziyinan.ui.activity.EventStatistyActivity;
import com.sdxxtop.shuziyinan.ui.activity.ExamineActivity;
import com.sdxxtop.shuziyinan.ui.activity.FlyDataListActivity;
import com.sdxxtop.shuziyinan.ui.activity.FlyEventDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.FlyEventReportActivity;
import com.sdxxtop.shuziyinan.ui.activity.GACEventDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.GACPatrolDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.GrantCompanyReportActivity;
import com.sdxxtop.shuziyinan.ui.activity.GrantEventReportActivity;
import com.sdxxtop.shuziyinan.ui.activity.GrantGridReportActivity;
import com.sdxxtop.shuziyinan.ui.activity.GridEventActivity;
import com.sdxxtop.shuziyinan.ui.activity.GridMapActivity;
import com.sdxxtop.shuziyinan.ui.activity.GridreportUserreportActivity;
import com.sdxxtop.shuziyinan.ui.activity.HomeActivity;
import com.sdxxtop.shuziyinan.ui.activity.LoginActivity;
import com.sdxxtop.shuziyinan.ui.activity.MonitorMapActivity;
import com.sdxxtop.shuziyinan.ui.activity.NewsDetailsActivity;
import com.sdxxtop.shuziyinan.ui.activity.PartEventListActivity;
import com.sdxxtop.shuziyinan.ui.activity.PatrolAddDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.PatrolPathActivity;
import com.sdxxtop.shuziyinan.ui.activity.PatrolRecordActivity;
import com.sdxxtop.shuziyinan.ui.activity.ReCheckActivity;
import com.sdxxtop.shuziyinan.ui.activity.SafeStaffDetail2Activity;
import com.sdxxtop.shuziyinan.ui.activity.SafeStaffDetailActivity;
import com.sdxxtop.shuziyinan.ui.activity.SectionEventActivity;
import com.sdxxtop.shuziyinan.ui.activity.SplashActivity;
import com.sdxxtop.shuziyinan.ui.activity.TaskAgentsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(HomeActivity homeActivity);

    void inject(LoginActivity loginActivity);
    void inject(NewsDetailsActivity newsDetailsActivity);
    void inject(ContactActivity contactActivity);
    void inject(EventReportActivity eventReportActivity);
    void inject(PatrolRecordActivity patrolRecordActivity);
    void inject(EventReportListActivity activity);
    void inject(EventReportDetailActivity activity);
    void inject(ExamineActivity activity);
    void inject(EventReportDetailSecondActivity activity);
    void inject(GridMapActivity activity);
    void inject(SplashActivity activity);
    void inject(TaskAgentsActivity activity);
    void inject(ContactSearchActivity activity);
    void inject(GrantEventReportActivity activity);
    void inject(EventStatistyActivity activity);
    void inject(GrantCompanyReportActivity activity);
    void inject(PatrolPathActivity activity);
    void inject(GACEventDetailActivity activity);
    void inject(SafeStaffDetailActivity activity);
    void inject(SafeStaffDetail2Activity activity);
    void inject(PartEventListActivity activity);
    void inject(GACPatrolDetailActivity activity);
    void inject(GrantGridReportActivity activity);
    void inject(GridreportUserreportActivity activity);
    void inject(EventDiscretionListActivity activity);
    void inject(EventDiscretionReportActivity activity);
    void inject(PatrolAddDetailActivity activity);
    void inject(ReCheckActivity activity);
    void inject(CenterMessageActivity activity);
    void inject(CenterMessage2Activity activity);
    void inject(MonitorMapActivity activity);
    void inject(DeviceDataDetailActivity activity);
    void inject(DeviceListActivity activity);
    void inject(DeviceCenterMsgActivity activity);
    void inject(DeviceWarnDetailActivity activity);
    void inject(SectionEventActivity activity);
    void inject(EventMoveActivity activity);
    void inject(FlyDataListActivity activity);
    void inject(FlyEventDetailActivity activity);
    void inject(FlyEventReportActivity activity);
    void inject(EventDetailActivity activity);
    void inject(GridEventActivity activity);

}
