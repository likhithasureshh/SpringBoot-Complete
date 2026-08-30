package com.module_3.Projection.entities;

import com.module_3.Projection.enums.BloodGroup;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String email;
    @Enumerated(EnumType.STRING)
    BloodGroup bloodGroup;
    String gender;
    Integer age;
    @CreationTimestamp
    LocalDateTime createdAt;
    @OneToOne
    @JoinColumn(unique = true,name = "patient_insurance")
    private Insurance insurance;

    @OneToMany(mappedBy = "patient")
    Set<Appointment> appointmentSet = new HashSet<>();
}
