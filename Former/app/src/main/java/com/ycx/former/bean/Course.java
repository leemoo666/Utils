package com.ycx.former.bean;

import java.io.Serializable;

/**
 * Created by 李小明 on 17/4/13.
 * 邮箱:287907160@qq.com
 */

public class Course implements Serializable {

    private String courseName;

    private String courseId;

    public Course(String courseName, String courseId) {
        this.courseName = courseName;
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
