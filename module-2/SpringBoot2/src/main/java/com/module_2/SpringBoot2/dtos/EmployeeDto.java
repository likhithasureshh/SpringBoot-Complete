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
    @NotNull(message = "Employee name cannot be null")
    @NotEmpty(message = "Employee name cannot be empty")
    @NotBlank(message = "Employee name cannot be blank")
    @Size(min = 3,max = 10,message = "The length of the name should be in the range [3,10]")
    String name;
    @Email
    String email;
    @Digits(integer = 5,fraction = 2,message = "The digits length : integer:5 and fraction: 2")
    @DecimalMin(value = "100.50")
    @DecimalMax("10000.00")
    Double salary;
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "The role of the employee should be ADMIN or USER")
    @EmployeeRoleValidator
    String role;
    @Max(value = 80,message = "The maximum age is 80")
    @Min(value = 18,message = "The minimum age is 18")
    @Positive
    Integer age;
    @AssertTrue
    Boolean isActive;

    @PastOrPresent(message = "The dateOfJoining should be in past or present")
    LocalDate dateOfJoining;

}
