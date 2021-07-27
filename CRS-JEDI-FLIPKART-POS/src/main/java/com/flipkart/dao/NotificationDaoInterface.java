package com.flipkart.dao;

import java.sql.SQLException;

public interface NotificationDaoInterface {


    /**
     * Send Notification using SQL commands
     * @param studentId: student to be notified
     * @param mode: mode of payment used
     */
    void sendNotification(String studentId,String mode);
}
