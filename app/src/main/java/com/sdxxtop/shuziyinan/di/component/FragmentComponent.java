package com.sdxxtop.shuziyinan.di.component;

import android.app.Activity;

import com.sdxxtop.shuziyinan.di.module.FragmentModule;
import com.sdxxtop.shuziyinan.di.qualifier.FragmentScope;
import com.sdxxtop.shuziyinan.ui.fragment.CourseListFragment;
import com.sdxxtop.shuziyinan.ui.fragment.DataMonitoringFragment;
import com.sdxxtop.shuziyinan.ui.fragment.EventListFragment;
import com.sdxxtop.shuziyinan.ui.fragment.GridEventFragment;
import com.sdxxtop.shuziyinan.ui.fragment.HomeFragment;
import com.sdxxtop.shuziyinan.ui.fragment.MineFragment;
import com.sdxxtop.shuziyinan.ui.fragment.NewsListFragment;
import com.sdxxtop.shuziyinan.ui.fragment.OrganizationListFragment;
import com.sdxxtop.shuziyinan.ui.fragment.SectionEventFragment;
import com.sdxxtop.shuziyinan.ui.fragment.WorkFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {FragmentModule.class}, dependencies = AppComponent.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(HomeFragment homeFragment);
    void inject(DataMonitoringFragment fragment);

    void inject(NewsListFragment newsListFragment);
    void inject(CourseListFragment fragment);
    void inject(MineFragment fragment);
    void inject(SectionEventFragment fragment);
    void inject(WorkFragment fragment);
    void inject(GridEventFragment fragment);
    void inject(OrganizationListFragment fragment);
    void inject(EventListFragment fragment);
}
