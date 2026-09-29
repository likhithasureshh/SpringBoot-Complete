package com.module_2.SpringBoot2.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidation implements ConstraintValidator<EmployeeRoleValidator,String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext context) {
        List<String> roles = List.of("ADMIN","USER");
        return roles.contains(inputRole);
    }
}
