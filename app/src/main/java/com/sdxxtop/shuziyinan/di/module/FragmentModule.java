package com.sdxxtop.shuziyinan.di.module;

import android.app.Activity;


import com.sdxxtop.shuziyinan.di.qualifier.FragmentScope;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity getActivity() {
        return mFragment.getActivity();
    }
}
