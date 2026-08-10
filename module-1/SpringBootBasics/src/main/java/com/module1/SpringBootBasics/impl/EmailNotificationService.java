package com.module1.SpringBootBasics.impl;

import com.module1.SpringBootBasics.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//@Primary
@Component
@Qualifier("emailNotify")
//@ConditionalOnProperty(name = "notification.type",havingValue = "email")
public class EmailNotificationService implements NotificationService {

    @Override
    public void notify(String message) {
        System.out.println("EmailNotifificationService called : "+message);
    }
}
