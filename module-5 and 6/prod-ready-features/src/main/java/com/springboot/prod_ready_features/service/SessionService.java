package com.springboot.prod_ready_features.service;

import com.springboot.prod_ready_features.entities.User;

public interface SessionService {
    void generateSessionForUser(String refreshToken, User user);

    void validateSession(String refreshToken);
}
