package com.module_3.Projection.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    LocalDateTime createdAt;
    @OneToOne
    @JoinColumn(nullable = false)
    Doctor headDoctor;

    @ManyToMany(mappedBy = "departments")
    Set<Doctor> doctorSet = new HashSet<>();
}
