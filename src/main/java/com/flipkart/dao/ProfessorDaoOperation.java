package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.GradesAlreadyGivenException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorDaoOperation implements ProfessorDaoInterface {

    private final Connection conn;
    public ProfessorDaoOperation(){
        conn = DBConnector.getInstance();
    }

    @Override
    public ArrayList<Course> getCourseByProf(String profId) {

        ArrayList<Course> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM course_catalog WHERE course_code in (select course_code from instructor where profId = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , profId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                String code = rs.getString("course_code");
                String description = rs.getString("description");
                String course_name = rs.getString("course_name");
                Integer seats = rs.getInt("seats");

                Course tmp_course = new Course(code, course_name , seats , description);
                result.add(tmp_course);
            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Student> getEnrolledStudents(String courseCode) {

        Connection conn = DBConnector.getInstance();
        return null;
    }

    @Override
    public void addGrades(String studentId, String courseCode, String grade) throws GradesAlreadyGivenException {

        if (checkIfGradesAlreadyGiven(studentId , courseCode)){
            throw new GradesAlreadyGivenException(studentId);
        }

    }

    @Override
    public void chooseCourse(String profId , String courseCode) throws CourseAlreadyRegisteredException {

        Connection conn = DBConnector.getInstance();
        if( checkIfSignedUp(profId , courseCode))
            throw new CourseAlreadyRegisteredException(courseCode);

        final String sql = "INSERT INTO instructor values (? , ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , profId);
            stmt.setString(2 , courseCode);
            stmt.executeQuery();
            System.out.println("Course added successfully .");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private boolean checkIfSignedUp(String profId , String courseCode){

        final String sql = "SELECT * from instructor where profId = ? and course_code = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , profId);
            stmt.setString(2 , courseCode);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;

    }

    private boolean checkIfGradesAlreadyGiven(String studentId , String courseCode){

        Connection conn = DBConnector.getInstance();

        try{
            final String sql = "SELECT * grades where studentId = ? and courseCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            stmt.setString(2 , courseCode);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
//        ArrayList<Course>  courses = operation.getCourseByProf("Prof001");
//        System.out.println(courses);

        System.out.println(operation.checkIfSignedUp("Prof001" , "CS004"));
    }
}
