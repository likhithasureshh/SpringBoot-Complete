package com.module1.SpringBootBasics.impl;

import com.module1.SpringBootBasics.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("smsNotify")
//@ConditionalOnProperty(name = "notification.type",havingValue = "sms")
public class SmsNotificationService implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("SmsNotificationService called : "+message);
    }
}
