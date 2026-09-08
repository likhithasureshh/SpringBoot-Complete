package com.springboot.prod_ready_features.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/posts").permitAll()
                        .requestMatchers("/posts/**").hasAnyRole("ADMIN"))
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                //.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService()
    {
        UserDetails normalUser = User
                .withUsername("likitha")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails adminUser = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser,adminUser);
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
