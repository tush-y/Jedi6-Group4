package com.flipkart.validator;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.dao.DBConnector;
import com.flipkart.exceptions.InvalidCredentialsException;
import com.flipkart.exceptions.UserNotApprovedException;
import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.session.Session;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    public User login(String username , String password) throws UserNotApprovedException , InvalidCredentialsException {

        Connection conn = DBConnector.getInstance();
        Logger logger = Logger.getLogger(Authentication.class);
        final String sql = "SELECT * from users left join professor on profId = users.userId left join student on studentID = userId where userId = ? and password = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , username);
            stmt.setString(2 , password);
            ResultSet rs = stmt.executeQuery();
            logger.trace(rs);
            if(rs.next()){

                String role = rs.getString("role");
                logger.trace(role);
                switch (role) {
                    case "Professor":
                        Professor professor = new Professor();
                        professor.setId(rs.getString("userId"));
                        professor.setDepartment(rs.getString("department"));
                        professor.setName(rs.getString("name"));
                        professor.setRole(Role.PROF);
                        professor.setDesignation(rs.getString("designation"));
                        return professor;
                    case "Admin":
                        Admin admin = new Admin();
                        admin.setId(rs.getString("userId"));
                        admin.setName(rs.getString("name"));
                        admin.setRole(Role.ADMIN);
                        return admin;

                    case "Student":
                        if(!rs.getBoolean("isApproved")){
                            throw new UserNotApprovedException();
                        }
                        Student student = new Student();
                        student.setName(rs.getString("name"));
                        student.setBranch(rs.getString("branch"));
                        student.setId(rs.getString("userId"));
                        student.setRole(Role.STUDENT);
                        return student;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new InvalidCredentialsException();
    }

    public void register(String id , String name , String password){

        Connection conn = DBConnector.getInstance();
        try{
            final String sql = "INSERT INTO users values (? , ? , ? , ? , ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , id);
            stmt.setString(2 , name);
            stmt.setString(3 , "Student");
            stmt.setInt(4 , 0);
            stmt.executeQuery();
            System.out.println("Signup Success .");

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
