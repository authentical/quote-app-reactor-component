package com.example.quoteappws;


import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void initiateNotification(NotificationData notificationData)
            throws InterruptedException {

        System.out.println("Notification service started for "
                + "Notification ID: " + notificationData.getId());

        /*to illustrate real life scenario of sending messages via
        SMS gateway or Email gateway, we're intentionally introducing a
        5 seconds delay in the initiateNotification method by
        */
        Thread.sleep(5000);

        System.out.println("Notification service ended for "
                + "Notification ID: " + notificationData.getId());
    }
}