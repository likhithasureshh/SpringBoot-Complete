package com.module_3.Projection.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.print.Doc;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    LocalDateTime appointmentTime;
    String reason;
    String status;
    @ManyToOne
    @JoinColumn(nullable = false)
    Patient patient;

    @ManyToOne
    @JoinColumn(nullable = false )
    Doctor doctor;

}
