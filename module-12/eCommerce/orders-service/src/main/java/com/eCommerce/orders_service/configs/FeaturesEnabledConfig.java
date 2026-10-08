package com.eCommerce.orders_service.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RefreshScope
public class FeaturesEnabledConfig {


    @Value("${feature.enabled}")
    private Boolean isFeatureEnabled;
}
