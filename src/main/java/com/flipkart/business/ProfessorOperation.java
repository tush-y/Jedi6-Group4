package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.constant.Grade;
import com.flipkart.dao.CatalogDaoOperation;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;

import java.util.ArrayList;

public class ProfessorOperation implements ProfessorOperationInterface {

    Professor professor;
    public ProfessorOperation(Professor professor){
        this.professor = professor;
    }
    @Override
    public void addGrades(String studentId, Grade value) {

    }

    @Override
    public void chooseCourse(String courseCode) throws CourseAlreadyRegisteredException {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        operation.chooseCourse(professor.getId() , courseCode);
    }

    @Override
    public void viewEnrolledStudent(String courseCode) {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();

    }

    @Override
    public void viewCourses() {

        ArrayList<Course> enrolledCourses = new ProfessorDaoOperation().getCourseByProf(professor.getId());
        System.out.println("============================================================");
        for(Course course : enrolledCourses){
            System.out.println(course.toString());
        }
        System.out.println("============================================================");

    }
    public void showAllCourses(){

        ArrayList<Course> allCourses = new CatalogDaoOperation().getAllCourses();
        System.out.println("============================================================");
        for(Course course : allCourses){
            System.out.println(course.toString());
        }
        System.out.println("============================================================");
    }
}
