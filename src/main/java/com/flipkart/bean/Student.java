package com.flipkart.bean;

import java.util.ArrayList;

public class Student extends User {

    private String branch;
    private ArrayList<Course> courses;

    public Student(String name){

        setName(name);
    }

    public String getBranch() {
        return branch;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setCourses(Course course) {
        this.courses.add(course);
    }
}
