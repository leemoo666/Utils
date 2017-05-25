package com.ycx.former.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李小明 on 17/4/13.
 * 邮箱:287907160@qq.com
 */

public class Student implements Serializable {

    private String name;

    private String id;

    private int age;

    private List<Course> course;

    public Student(String name, String id, int age, List<Course> course) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.course = course;
    }

    public Student(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}';
    }
}
