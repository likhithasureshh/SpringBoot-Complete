package com.springboot.prod_ready_features.configs;

import com.springboot.prod_ready_features.audits.AuditWareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditWareImpl")
public class AppConfig {
     @Bean
     public ModelMapper modelMapper()
     {
         return new ModelMapper();
     }
     @Bean
     public AuditWareImpl getAuditWareImpl()
     {
         return new AuditWareImpl();
     }


}
