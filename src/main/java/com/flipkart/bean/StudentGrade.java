package com.flipkart.bean;

public class StudentGrade {

    private String courseCode;
    private String grade;

    public StudentGrade(String courseCode, String grade) {
        this.courseCode = courseCode;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
