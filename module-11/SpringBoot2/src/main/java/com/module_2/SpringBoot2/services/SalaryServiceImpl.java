package com.module_2.SpringBoot2.services;

import com.module_2.SpringBoot2.advices.exceptions.ResourceNotFoundException;
import com.module_2.SpringBoot2.entities.EmployeeEntity;
import com.module_2.SpringBoot2.entities.SalaryEntity;
import com.module_2.SpringBoot2.repositories.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisSubscribedConnectionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SalaryServiceImpl {
    private final SalaryRepository salaryRepository;


    public void createSalaryAccount(EmployeeEntity employee)
    {
        SalaryEntity salaryEntity = SalaryEntity.builder()
                .balance(BigDecimal.valueOf(0))
                .employeeEntity(employee)
                .build();
         salaryRepository.save(salaryEntity);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public SalaryEntity incrementBalance(Long id) {
        SalaryEntity salaryEntity = salaryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("salary Account Not found"));
        BigDecimal newBalance = salaryEntity.getBalance().add(BigDecimal.valueOf(1));
        salaryEntity.setBalance(newBalance);
        return salaryRepository.save(salaryEntity);
    }
}
