package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

public interface AdminDaoInterface {
    public void addCourse(Course course);
    public void removeCourse(String courseCode);
    public void addProfessor(Professor professor);
    public void approveStudent(String id);
}
