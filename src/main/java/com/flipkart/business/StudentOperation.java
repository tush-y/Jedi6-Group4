package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.dao.StudentDaoOperation;

public class StudentOperation implements StudentOperationInterface {
    Student student;
    public StudentOperation(Student student){
        this.student = student;
    }
    @Override
    public void viewEnrolledCourses(String studentId) {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.viewEnrolledCourses(studentId);


    }


    @Override
    public void addCourse(String studentId, String courseCode) {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.dropCourse(studentId , courseCode);

    }

    @Override
    public void register(String studentId) {

    }
    @Override
    public void dropCourse(String studentId, String courseCode) {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.dropCourse(studentId , courseCode);


    }

    @Override
    public void viewGradeCard(String studentId) {

    }
}
