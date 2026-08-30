package com.module_3.Projection.service;

import com.module_3.Projection.entities.Appointment;
import com.module_3.Projection.entities.Doctor;
import com.module_3.Projection.entities.Patient;
import com.module_3.Projection.repositories.AppointmentRepository;
import com.module_3.Projection.repositories.DoctorRepository;
import com.module_3.Projection.repositories.PatientRepositories;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final PatientRepositories patientRepositories;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment saveAppointment(Appointment appointment,Long patientId,Long doctorId)
    {
        Patient patient = patientRepositories.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        Appointment appointment1 = appointmentRepository.save(appointment);
        return appointment;
    }

    @Transactional
    public Appointment deleteAppointment(Long id)
    {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        appointmentRepository.deleteById(id);
        return appointment;
    }
}
