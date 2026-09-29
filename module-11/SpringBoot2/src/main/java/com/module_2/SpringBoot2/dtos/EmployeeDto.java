package com.module_2.SpringBoot2.dtos;

import com.module_2.SpringBoot2.annotations.EmployeeRoleValidator;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {
    Long id;

    String name;
    String email;

    Double salary;
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "The role of the employee should be ADMIN or USER")
    String role;


    Integer age;

    Boolean isActive;


    LocalDate dateOfJoining;

}
