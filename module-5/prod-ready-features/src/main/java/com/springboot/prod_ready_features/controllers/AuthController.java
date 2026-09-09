package com.springboot.prod_ready_features.controllers;

import com.springboot.prod_ready_features.dtos.LoginDTO;
import com.springboot.prod_ready_features.dtos.SignUpDTO;
import com.springboot.prod_ready_features.dtos.UserDTO;
import com.springboot.prod_ready_features.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO)
    {
        return ResponseEntity.ok(authService.signUp(signUpDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response)
    {
        String token = authService.login(loginDTO);
        Cookie cookie = new Cookie("token",token);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
