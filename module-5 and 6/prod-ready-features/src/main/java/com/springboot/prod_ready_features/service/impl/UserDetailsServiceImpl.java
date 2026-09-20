package com.springboot.prod_ready_features.service.impl;

import com.springboot.prod_ready_features.entities.User;
import com.springboot.prod_ready_features.exceptions.ResourceNotFoundException;
import com.springboot.prod_ready_features.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with email "+username));
    }

    public User getUserByUserId(Long userId)
    {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User is not found with id: "+userId));
    }

    public User findByUserName(String email)
    {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User save(User user1) {
        return userRepository.save(user1);
    }
}
