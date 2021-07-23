package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentOperationInterface {

    void viewEnrolledCourses(String studentId);
    void addCourse(String studentId , String courseCode);
    void register(String studentId);
    void dropCourse(String courseCode);
    void viewGradeCard(String studentId);
}
