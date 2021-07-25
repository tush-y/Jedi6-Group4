package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.SQLqueries;
import com.flipkart.input.Helper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoOperation implements StudentDaoInterface {
    private final Connection conn;
    private PreparedStatement stmt = null;
    private static Logger logger = Logger.getLogger(StudentDaoOperation.class);
    public StudentDaoOperation(){
        conn = DBConnector.getInstance();
    }

    public void viewEnrolledCourses(String studentId)
    {
        try{
            stmt = conn.prepareStatement("SELECT * FROM instructor;");
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

    /**
     * Method to add course in database
     * @param courseCode : code for selected course
     * @param studentId : ID of student
     * @return void
     * @throws SQLException
     */

    public void addCourse(String studentId, String courseCode) throws SQLException {
        try
        {
            stmt = conn.prepareStatement(SQLqueries.NUMBER_OF_REGISTERED_COURSES);
            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.getInt("count(studentId)") < 6) {
                stmt = conn.prepareStatement(SQLqueries.ADD_COURSE);
                stmt.setString(1, studentId);
                stmt.setString(2, courseCode);

                stmt.executeUpdate();

                stmt = conn.prepareStatement(SQLqueries.DECREASE_SEATS);
                stmt.setString(1, courseCode);
                stmt.executeUpdate();
            }
            else {
                logger.warn("Maximum Courses added. Drop Course to add more.");
            }
        }
        catch (SQLException e)
        {
            logger.info(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }
    }

    /**
     * Drop Course selected by student
     * @param courseCode : code for selected course
     * @param studentId : ID of student
     * @return void
     * * @throws SQLException
     */

    public void dropCourse(String studentId, String courseCode) throws SQLException {
        try
        {
            stmt = conn.prepareStatement(SQLqueries.NUMBER_OF_REGISTERED_COURSES);
            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.getInt("count(studentId)") > 4) {
                stmt = conn.prepareStatement(SQLqueries.DROP_COURSE_QUERY);
                stmt.setString(1, courseCode);
                stmt.setString(2, studentId);
                stmt.execute();

                stmt = conn.prepareStatement(SQLqueries.INCREASE_SEATS);
                stmt.setString(1, courseCode);
                stmt.execute();
            }
            else {
                logger.warn("Can't register less than 4 courses. Add the course first.");
            }
        }
        catch(Exception e)
        {
            logger.info(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }
    }

    /**
     * Method to view grade card of the student
     * @param studentId
     * @throws SQLException
     * @return Student's grade card
     */

    public List<StudentGrade> viewGradeCard(String studentId) throws SQLException {

        List<StudentGrade> gradeCard = new ArrayList<>();
        try
        {
            stmt = conn.prepareStatement(SQLqueries.IS_APPROVED);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if(rs.getBoolean("isApproved")) {
                stmt = conn.prepareStatement(SQLqueries.VIEW_GRADE);
                stmt.setString(1, studentId);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String courseCode = rs.getString("courseCode");
                    String grade = rs.getString("grade");
                    StudentGrade obj = new StudentGrade(courseCode, grade);
                    gradeCard.add(obj);
                }
            }
            else {
                logger.warn("Grades are not approved by Admin");
            }
        }
        catch(SQLException e)
        {
            logger.info(e.getMessage());
        }
        catch(Exception e)
        {
            logger.info(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }
        return gradeCard;
    }

    public void payFees(String studentId) {

        Connection conn = DBConnector.getInstance();

        try{
            String sql = "select * from students where student_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String status = rs.getString("fees_paid");
            if(status.equals("Yes"))
                System.out.println("Fees Already Paid");
            else {
                System.out.println("Choose method to pay:");
                System.out.println("1. Debit / Credit Card");
                System.out.println("2. Netbanking");
                System.out.println("3. UPI");
                Integer i = Helper.scanInt();
                switch(i) {
                    case 1:  String cardNumber = Helper.scanString("Card Number");
                             String pin = Helper.scanString("PIN");
                                break;
                    case 2:  String bankingId = Helper.scanString("Customer ID");
                             String password = Helper.scanString("Password");
                                break;
                    case 3:  String UPI = Helper.scanString("UPI ID");
                             String upiPin = Helper.scanString("UPI PIN");
                                break;
                    default: System.out.println("invalid input. Enter Again.");
                                payFees(studentId);
                }
                sql = "update students set fees_paid = 'Yes'  where student_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1 , studentId);
                stmt.executeUpdate();
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();

        }
    }
}
