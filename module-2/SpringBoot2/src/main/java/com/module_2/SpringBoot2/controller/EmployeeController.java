package com.module_2.SpringBoot2.controller;

import com.module_2.SpringBoot2.dtos.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping(path = "/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
        return new EmployeeDto(id,"Likitha",24,true, LocalDate.of(2026,8,18));
    }

    @GetMapping
    public String getEmployeeByAge(@RequestParam(required = false) Integer age,
                                   @RequestParam(required = false) String sortBy)
    {
        return "Hi my age is "+age+" im sorted by "+sortBy;
    }

    @PostMapping
    public String post()
    {
        return "Hi from post!";
    }

    @PutMapping
    public String put()
    {
        return "Hi from put!";
    }
}
