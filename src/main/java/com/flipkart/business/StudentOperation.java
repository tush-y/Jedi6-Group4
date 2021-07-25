package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.dao.StudentDaoOperation;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentOperation implements StudentOperationInterface {
    Student student;
    private static Logger logger = Logger.getLogger(StudentOperation.class);

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
        try {
            operation.addCourse(student.getId() , courseCode);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void register() {
    }

    @Override
    public void dropCourse(String courseCode) {
        StudentDaoOperation operation=new StudentDaoOperation();

        try {
            operation.dropCourse(student.getId() , courseCode);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void viewGradeCard() {
        StudentDaoOperation operation=new StudentDaoOperation();
        List<StudentGrade> gradeCard = new ArrayList<>();

        try {
            gradeCard = operation.viewGradeCard(student.getId());
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        if(gradeCard.isEmpty())
        {
            logger.info("You haven't registered for any course");
            return;
        }
        logger.info(String.format("%-10s %-10s","COURSE CODE", "GRADE"));

        for(StudentGrade obj : gradeCard)
        {
            logger.info(String.format("%-10s %-10s",obj.getCourseCode(),obj.getGrade()));
        }
    }

    @Override
    public void payFees() {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.payFees(student.getId());
    }
}
