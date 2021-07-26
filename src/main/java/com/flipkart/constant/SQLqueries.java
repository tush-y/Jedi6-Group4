package com.flipkart.constant;

public class SQLqueries {

    // Student Queries
    public static final String DECREASE_SEATS="update courseCatalog set seats = seats-1 where courseCode = ? ";
    public static final String ADD_COURSE="insert into registeredCourse (studentId,courseCode) values ( ? , ? )";
    public static final String DROP_COURSE_QUERY = "delete from registeredcourse where courseCode = ? AND studentId = ?;";
    public static final String INCREASE_SEATS  = "update courseCatalog set seats = seats + 1 where  courseCode = ?;";
    public static final String NUMBER_OF_REGISTERED_COURSES=" select count(studentId) as cnt from registeredCourse where studentId = ?;";
    public static final String VIEW_GRADE = "select courseCode,grade from registeredCourse where studentId = ?;";
    public static final String IS_APPROVED=" select isApproved from registeredCourse where studentId=?; ";
    public static final String ADD_PAYMENT="INSERT INTO payment values ( ? , ? , ?)";
    public static final String SELECT_PAYMENT_ROW=  "select * from payment where studentId = ?";

}
