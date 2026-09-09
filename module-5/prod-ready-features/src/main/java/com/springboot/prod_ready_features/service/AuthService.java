package com.springboot.prod_ready_features.service;

import com.springboot.prod_ready_features.dtos.LoginDTO;
import com.springboot.prod_ready_features.dtos.SignUpDTO;
import com.springboot.prod_ready_features.dtos.UserDTO;

public interface AuthService {
    UserDTO signUp(SignUpDTO signUpDTO);

    String login(LoginDTO loginDTO);
}
