package com.flipkart.validator;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.dao.DBConnector;
import com.flipkart.session.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    public User login(String username , String password){

        Connection conn = DBConnector.getInstance();

        final String sql = "SELECT * from users where user_id = ? and password = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , username);
            stmt.setString(2 , password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){

                String role = rs.getString("role");
                switch (role) {
                    case "Professor":
                        return new Professor(rs.getString("user_id") , rs.getString("name") , rs.getString("password") , Role.PROF);
                    case "Admin":
                        return new Admin(
                                rs.getString("user_id"),
                                rs.getString("name"),
                                rs.getString("password"),
                                Role.PROF
                        );
                    case "Student":
                        return new Student(
                                rs.getString("user_id"),
                                rs.getString("name"),
                                rs.getString("password"),
                                Role.PROF
                        );
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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
