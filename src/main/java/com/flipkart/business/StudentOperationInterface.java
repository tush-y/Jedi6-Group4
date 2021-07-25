package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentOperationInterface {

    void viewEnrolledCourses();
    void addCourse(String courseCode);
    void register();
    void dropCourse(String courseCode);
    void viewGradeCard();
    void payFees();
}
