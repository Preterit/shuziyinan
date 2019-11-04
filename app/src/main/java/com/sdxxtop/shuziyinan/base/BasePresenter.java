package com.sdxxtop.shuziyinan.base;

public interface BasePresenter<T> {
    void attachView(T t);

    void detachView();
}
