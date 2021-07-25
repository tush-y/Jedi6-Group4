package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.constant.Grade;
import com.flipkart.dao.CatalogDaoOperation;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.CourseNotTaughtException;
import com.mysql.cj.log.Log;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ProfessorOperation implements ProfessorOperationInterface {

    Professor professor;
    private Logger logger = Logger.getLogger(ProfessorOperation.class);
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
        try {
            ArrayList<ArrayList<String>> data = operation.getEnrolledStudents(professor.getId() , courseCode);
            logger.trace("============================================================");
            for(ArrayList<String> info : data){
                logger.info(info);
            }
            logger.trace("============================================================");
        }
        catch (CourseNotTaughtException ex){
            logger.info(ex.getMessage());
        }
    }

    @Override
    public void viewCourses() {

        ArrayList<Course> enrolledCourses = new ProfessorDaoOperation().getCourseByProf(professor.getId());
        logger.trace("============================================================");
        for(Course course : enrolledCourses){
            logger.info(course.toString());
        }
        logger.trace("============================================================");

    }
    public void showAllCourses(){

        ArrayList<Course> allCourses = new CatalogDaoOperation().getAllCourses();
        logger.trace("============================================================");
        for(Course course : allCourses){
            logger.info(course.toString());
        }
        logger.trace("============================================================");
    }
}
