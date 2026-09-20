package com.springboot.prod_ready_features.dtos;

import com.springboot.prod_ready_features.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {
    String name;
    String password;
    String email;
    Set<Role> roles;
}
