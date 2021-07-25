package com.flipkart.constant;

public class SQLQueriesConstant {

    // All the Professor queries will be here
    public static final String viewEnrolledStudents = "SELECT  rC.studentID , name , branch from registeredCourse rC\n" +
            "    JOIN users on users.user_id = rC.studentID\n" +
            "    JOIN student on student.studentID = rC.studentID\n" +
            "    WHERE courseCode = ?;";
    public static final String getProfWithCourse = "SELECT * FROM instructor where profId = ? and course_code = ?";
    public static final String addGrades = "update registeredCourse set grade = ? where studentId = ? and courseCode = ?";
}
