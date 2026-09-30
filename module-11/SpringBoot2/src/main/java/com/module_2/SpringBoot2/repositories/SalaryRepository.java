package com.module_2.SpringBoot2.repositories;


import com.module_2.SpringBoot2.entities.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<SalaryEntity,Long> {
}
