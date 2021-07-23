package com.flipkart.dao;

import com.apple.eawt.event.SwipeListener;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorDaoOperation implements ProfessorDaoInterface {


    @Override
    public ArrayList<Course> getCourseByProf(String profId) {

        Connection conn = DBConnector.gtInstance();
        ArrayList<Course> result = new ArrayList<>();
        try{
            PreparedStatement stmt = conn.prepareStatement(String.format("Select * from instructor where profId = %s" , profId));
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> tmp = new ArrayList<>();
            while(rs.next()){

                String code = rs.getString("course_code");
                tmp.add(code);
            }
            String sql = "Select * from course_catalog where course code in (";
            for (String code : tmp){
                sql += tmp;
                sql += ",";
            }
            sql += ")";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

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
    public ArrayList<Student> getEnrolledStudents(String profId) {
        return null;
    }

    @Override
    public void addGrades(String studentId, String courseCode, String grade) {

    }
}
