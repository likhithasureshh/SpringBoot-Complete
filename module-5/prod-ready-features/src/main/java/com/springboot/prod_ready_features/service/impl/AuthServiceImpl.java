package com.springboot.prod_ready_features.service.impl;

import com.springboot.prod_ready_features.dtos.LoginDTO;
import com.springboot.prod_ready_features.dtos.LoginResponseDTO;
import com.springboot.prod_ready_features.dtos.SignUpDTO;
import com.springboot.prod_ready_features.dtos.UserDTO;
import com.springboot.prod_ready_features.entities.User;
import com.springboot.prod_ready_features.repositories.UserRepository;
import com.springboot.prod_ready_features.service.AuthService;
import com.springboot.prod_ready_features.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final SessionService sessionService;
    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent())
        {
            throw new BadCredentialsException("User already exists! You cannot signUp!");
        }
        User isToBeCreated = modelMapper.map(signUpDTO,User.class);
        isToBeCreated.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        User savedUser = userRepository.save(isToBeCreated);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        sessionService.generateSessionForUser(refreshToken,user);
        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);

    }

    @Override
    public LoginResponseDTO refresh(String refreshToken) {
        Long userId = jwtService.getUserId(refreshToken);
        sessionService.validateSession(refreshToken);
        User user = userDetailsService.getUserByUserId(userId);
        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDTO(userId,accessToken,refreshToken);
    }
}
