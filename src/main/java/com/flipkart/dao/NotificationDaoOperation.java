package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotificationDaoOperation implements NotificationDaoInterface{
    private final Connection conn;
    public NotificationDaoOperation(){
        conn = DBConnector.getInstance();
    }
    @Override
    public void sendNotification(String studentId, String mode)
    {
        final String sql = "INSERT INTO notifications values (? , ? ,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            stmt.setString(2 , mode);
            stmt.setString(3,"payment succesfull");

            stmt.executeUpdate();
            System.out.println("******* Payment Succesfully completed. ");
            System.out.println("****** Transaction Id is y27y88289199");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
