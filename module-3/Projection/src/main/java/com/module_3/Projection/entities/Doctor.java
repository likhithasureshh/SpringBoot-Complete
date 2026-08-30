package com.module_3.Projection.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String specialization;
    String email;
    LocalDateTime createdAt;

    @OneToMany(mappedBy = "doctor")
    Set<Appointment> appointmentSet=new HashSet<>();
    @OneToOne(mappedBy = "headDoctor")
    Department department;

    @ManyToMany
    @JoinTable
            (
                    name = "doctor_department",
                    joinColumns = @JoinColumn(name = "doctor_id"),
                    inverseJoinColumns = @JoinColumn(name = "department_id")
            )
    private Set<Department> departments = new HashSet<>();
}
