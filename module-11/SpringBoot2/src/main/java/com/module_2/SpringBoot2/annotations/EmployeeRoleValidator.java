package com.module_2.SpringBoot2.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(
        validatedBy = {EmployeeRoleValidation.class}
)
public @interface EmployeeRoleValidator {
    String message() default "The Role of the Employee should be USER or ADMIN";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
