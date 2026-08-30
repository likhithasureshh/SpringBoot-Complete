package com.module_3.Projection.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String policyNumber;
    String provider;
    LocalDate validUntil;
    LocalDateTime createdAt;

    @OneToOne(mappedBy = "insurance")
    @ToString.Exclude
    private Patient patient;

}
