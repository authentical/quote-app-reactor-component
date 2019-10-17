package com.example.quoteappws;


public interface NotificationService {

    void initiateNotification(NotificationData notificationData)
            throws InterruptedException;

}
