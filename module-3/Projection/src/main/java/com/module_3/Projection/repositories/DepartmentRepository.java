package com.module_3.Projection.repositories;

import com.module_3.Projection.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}