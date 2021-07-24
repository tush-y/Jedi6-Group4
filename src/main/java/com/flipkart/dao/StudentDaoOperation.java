package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoOperation implements StudentDaoInterface {
    private final Connection conn;
    public StudentDaoOperation(){
        conn = DBConnector.getInstance();
    }

    public void viewEnrolledCourses(String studentId)
    {
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM instructor;");
            String sql = "SELECT * FROM course_catalog WHERE course_code in (select course_code from students where student_id = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String course_name = rs.getString("course_name");
                System.out.println(course_name);
            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public void addCourse(String studentId, String courseCode)
    {
        Connection conn = DBConnector.getInstance();



        final String sql = "INSERT INTO students values (? , ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            stmt.setString(2 , courseCode);
            stmt.executeQuery();
            System.out.println("Successfully Assigned.");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void dropCourse(String studentId, String courseCode)
    {
        Connection conn = DBConnector.getInstance();



        final String sql = "DELETE from Students where student_id=? and course_code=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            stmt.setString(2 , courseCode);
            stmt.executeQuery();
            System.out.println("Deleted.");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
public static void main(String args[])
{
    StudentDaoOperation st=new StudentDaoOperation();
    st.addCourse("abdul","23");

}

}
