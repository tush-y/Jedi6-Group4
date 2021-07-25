package com.flipkart.dao;

import java.sql.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;


public class AdminDaoOperation implements AdminDaoInterface {

    private final Connection conn;
    public AdminDaoOperation()
    {
        conn = DBConnector.getInstance();
    }

    @Override
    public void addCourse(Course course)
    {
        try
         {
             String sql="insert into course_catalog(course_code, course_name, description, seats) values (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getDescription());

            stmt.setInt(4, course.getSeats());
            int row = stmt.executeUpdate();

            System.out.println(row +" Course Added");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public void removeCourse(String courseCode)
    {
        try
        {
            String sql="delete from course_catalog where course_code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, courseCode);
            int row = stmt.executeUpdate();

            System.out.println(row +" Course Removed");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public void addProfessor(Professor professor)
    {
        try
        {
            //insertion in instructor table
            String sql_prof="insert into professor(profId, profDesignation,profDep) values (?, ?, ?)";
            PreparedStatement stmt_prof = conn.prepareStatement(sql_prof);
            stmt_prof.setString(1, professor.getId());
            stmt_prof.setString(2, professor.getDesignation());
            stmt_prof.setString(3, professor.getDepartment());
            int row_prof= stmt_prof.executeUpdate();

            System.out.println(row_prof+" Professor Added in Professor");

            //insertion in user table
            String sql_user="insert into users(user_id, name,password, role) values (?, ?, ?, ?)";
            PreparedStatement stmt_user = conn.prepareStatement(sql_user);
            stmt_user.setString(1, professor.getId());
            stmt_user.setString(2, professor.getName());
            stmt_user.setString(3, "admin");
            stmt_user.setString(4, professor.getDesignation());
            int row_user = stmt_user.executeUpdate();

            System.out.println(row_user +" Professor Added in User");

        }
        catch(SQLException ex)
        {
           ex.printStackTrace();
         }
    }
    public void approveStudent(String id)
    {
        try
        {
            String sql="update student set isApproved = true where studentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            int row = stmt.executeUpdate();
            if(row==0)
            {
                System.out.println("Student with provided student id doesn't exist");
            }
            else
            {
                System.out.println("Student with student id: " + id + " is approved");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

}

