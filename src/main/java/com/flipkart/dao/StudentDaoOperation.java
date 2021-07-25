package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.input.Helper;

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


        final String sql = "INSERT INTO students values (? , ? ,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            stmt.setString(2 , courseCode);
            stmt.setString(3 , "No");
            stmt.executeUpdate();
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
            stmt.executeUpdate();
            System.out.println("Deleted.");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void payFees(String studentId) {

        Connection conn = DBConnector.getInstance();

        try{
            String sql = "select * from students where student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
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
                String str[]={"Debit / Credit Card","Netbanking","UPI"};
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
                NotificationDaoOperation notificationDaoOperation=new NotificationDaoOperation();
                notificationDaoOperation.sendNotification(studentId,str[i]);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();

        }
    }

public static void main(String args[])
{
    StudentDaoOperation st=new StudentDaoOperation();
    st.addCourse("abdul","23");

}

}
