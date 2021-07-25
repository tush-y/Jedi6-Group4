package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public class AdminOperation implements AdminOperationInterface {

    public void addCourse(Course course)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        operation.addCourse(course);
    }

    public void removeCourse(String courseCode)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        operation.removeCourse(courseCode);
    }

    public void approveStudent(String id)
    {

    }
    public void addProfessor(Professor professor)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        operation.addProfessor(professor);
    }
}
