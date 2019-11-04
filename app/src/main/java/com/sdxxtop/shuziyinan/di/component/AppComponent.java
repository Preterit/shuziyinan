package com.sdxxtop.shuziyinan.di.component;


import com.sdxxtop.shuziyinan.app.App;
import com.sdxxtop.shuziyinan.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    App getAppContext();
}
