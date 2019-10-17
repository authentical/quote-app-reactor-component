package com.example.quoteappws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;


@Service
public class NotificationConsumer implements Consumer<Event<NotificationData>> {

    @Autowired
    private NotificationService notificationService;

    @Override
    public void accept(Event<NotificationData> notificationDataEvent) {  // .getData() belongs to Event<>
        NotificationData notificationData = notificationDataEvent.getData(); //TODO Gets data from Event

        try {
            notificationService.initiateNotification(notificationData);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}