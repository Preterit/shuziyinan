package com.sdxxtop.shuziyinan.model.bean.course;

public class CourseHeaderBean extends BaseCourseDataBean {
    public String strHeader;
    public String color;

    @Override
    public int getItemType() {
        return TYPE_HEADER;
    }
}
