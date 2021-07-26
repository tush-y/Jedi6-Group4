package com.flipkart.constant;

import org.omg.CORBA.PUBLIC_MEMBER;

public class SQLQueriesConstant {

    // All the Professor queries will be here
    /**
     * This file contains constant queries .
     */
    public static final String viewEnrolledStudents = "SELECT  rC.studentID , name , branch from registeredCourse rC\n" +
            "    JOIN users on users.userid = rC.studentID\n" +
            "    JOIN student on student.studentID = rC.studentID\n" +
            "    WHERE courseCode = ?;";
    public static final String getProfWithCourse = "SELECT * FROM instructor where profId = ? and courseCode = ?";
    public static final String addGrades = "update registeredCourse set grade = ? where studentId = ? and courseCode = ?";








    // Users
    public static final String getUserByID = "SELECT userId from users WHERE userId = ?";
    public static final String getUserByIDAndPassword = "SELECT userId from users WHERE userId = ? and password = ?";
    public static final String updatePassword = "UPDATE users SET password = ? WHERE userId = ?";





    // Student
    public static final String addStudent = "INSERT INTO student values (? , ? , ?)";
    public static final String IS_APPROVED=" select isApproved from registeredCourse where studentId=?; ";
    public static final String IS_COURSE_REGISTERED = "select count(*) as cnt from registeredCourse where studentId = ? AND courseCode = ?;";
    public static final String GET_AVAILABLE_SEATS = "select seats from courseCatalog where courseCode = ?;";
    public static final String ADD_SINGLE_COURSE = "INSERT INTO registeredCourse values(? , ? ,'NA' , 0)";




    // Admin
    public static final String INSERT_COURSE = "insert into courseCatalog(courseCode, courseName, description, seats) values (?, ?, ?, ?)";
}
