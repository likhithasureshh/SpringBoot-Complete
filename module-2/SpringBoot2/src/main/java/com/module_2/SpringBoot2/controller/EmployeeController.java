package com.module_2.SpringBoot2.controller;

import com.module_2.SpringBoot2.dtos.EmployeeDto;
import com.module_2.SpringBoot2.entities.EmployeeEntity;
import com.module_2.SpringBoot2.repositories.EmployeeRepository;
import com.module_2.SpringBoot2.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.lang.ref.PhantomReference;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId)
    {
        Optional<EmployeeDto> employeeDtoOptional = employeeService.getEmployeeById(employeeId);
        return employeeDtoOptional.map(employeeDto -> ResponseEntity.ok(employeeDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAllEmployees()
    {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody @Valid EmployeeDto employeeDto)
    {
        return new ResponseEntity<>(employeeService.createNewEmployee(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEntireEmployeeById(@RequestBody EmployeeDto employeeDto,@PathVariable Long employeeId)
    {
        EmployeeDto employeeDto1 = employeeService.updateEntireEmployeeById(employeeDto,employeeId);
        return ResponseEntity.ok(employeeDto1);
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId)
    {
        Boolean result = employeeService.deleteEmployeeById(employeeId);
        if (result == false)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateFewFields(@PathVariable Long employeeId,
                                                       @RequestBody Map<String,Object> updates)
    {
        EmployeeDto employeeDto = employeeService.updateFewFields(employeeId,updates);
        if (employeeDto == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDto);
    }







}
