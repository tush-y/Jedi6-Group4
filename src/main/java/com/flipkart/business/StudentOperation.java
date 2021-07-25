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
    public void viewEnrolledCourses() {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.viewEnrolledCourses(student.getId());
    }

    @Override
    public void addCourse(String courseCode) {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.dropCourse(student.getId() , courseCode);
    }

    @Override
    public void register() {
    }

    @Override
    public void dropCourse(String courseCode) {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.dropCourse(student.getId() , courseCode);
    }

    @Override
    public void viewGradeCard() {

    }

    @Override
    public void payFees() {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.payFees(student.getId());
    }
}
