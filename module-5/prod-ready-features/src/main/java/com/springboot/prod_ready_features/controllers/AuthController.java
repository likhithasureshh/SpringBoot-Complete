package com.springboot.prod_ready_features.controllers;

import com.springboot.prod_ready_features.dtos.LoginDTO;
import com.springboot.prod_ready_features.dtos.LoginResponseDTO;
import com.springboot.prod_ready_features.dtos.SignUpDTO;
import com.springboot.prod_ready_features.dtos.UserDTO;
import com.springboot.prod_ready_features.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response)
    {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);
        Cookie cookie = new Cookie("refreshToken",loginResponseDTO.getRefreshToken());
        cookie.setSecure(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request)
    {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(()-> new AuthenticationServiceException("Refresh Token Not Found!"));
        LoginResponseDTO loginResponseDTO = authService.refresh(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);

    }
}
