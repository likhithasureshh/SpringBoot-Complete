package com.module_3.Projection.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.print.Doc;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    LocalDateTime appointmentTime;
    String reason;
    String status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
            @ToString.Exclude
    Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(nullable = false )
    Doctor doctor;

}
