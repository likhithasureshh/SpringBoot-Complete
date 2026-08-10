package com.module1.SpringBootBasics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PaymentService paymentService()
    {
        return new PaymentService();
    }
}
