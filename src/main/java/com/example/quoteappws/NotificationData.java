package com.example.quoteappws;

import lombok.Getter;
import lombok.Setter;


public class NotificationData {


    @Getter @Setter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private String email;
    @Getter @Setter private String mobile;


    public NotificationData() {
    }

    public NotificationData(long id, String name, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

}