package com.flipkart.bean;

import java.util.ArrayList;

public class Professor extends User {

    private String department;
    private String designation;
    private ArrayList<Course> courses;

    public Professor(){
        department = "CS";
        designation = "Assistant Professor";
        courses = new ArrayList<>();
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", courses=" + courses +
                '}';
    }
}
