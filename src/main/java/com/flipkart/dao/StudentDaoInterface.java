package com.flipkart.dao;

public interface StudentDaoInterface {
    void viewEnrolledCourses(String studentId);

    public void addCourse(String studentId, String courseCode);
    void payFees(String studentId);

}
