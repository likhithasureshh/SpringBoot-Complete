package com.module_2.SpringBoot2.repositories;


import com.module_2.SpringBoot2.entities.SalaryEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<SalaryEntity,Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SalaryEntity> findById(Long id);
}
