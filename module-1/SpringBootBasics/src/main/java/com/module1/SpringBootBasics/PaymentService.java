package com.module1.SpringBootBasics;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component
public class PaymentService {
    public void pay()
    {
        System.out.println("PaymentService called..");
    }

    @PostConstruct
    public void afterIni()
    {
        System.out.println("PaymentService bean is created and initialized..");
    }

    @PreDestroy
    public void beforeDestroy()
    {
        System.out.println("PaymentService bean is destroyed..");
    }
}
