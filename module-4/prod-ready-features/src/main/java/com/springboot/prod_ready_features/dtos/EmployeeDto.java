package com.springboot.prod_ready_features.dtos;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
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
