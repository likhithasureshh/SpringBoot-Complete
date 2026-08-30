package com.module_3.Projection;

import com.module_3.Projection.entities.Appointment;
import com.module_3.Projection.entities.Insurance;
import com.module_3.Projection.entities.Patient;
import com.module_3.Projection.service.AppointmentService;
import com.module_3.Projection.service.InsuranceService;
import com.module_3.Projection.service.PatientService;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {
     @Autowired
     private InsuranceService insuranceService;
     @Autowired
     private PatientService patientService;

     @Autowired
     private AppointmentService appointmentService;

     @Test
     public void test()
     {
//         Insurance insurance = Insurance.builder()
//                 .provider("HDFC")
//                 .validUntil(LocalDate.of(2027,1,1))
//                 .policyNumber("GUDDU34566")
//                 .build();
//         Insurance insurance1 = insuranceService.assignInsuranceToPatient(insurance,1L);
//        // System.out.println(insurance1);
//         Patient patient =patientService.deletePatientWithInsurance(1L);
//         System.out.println(patient);

         Appointment appointment = Appointment.builder()
                 .appointmentTime(LocalDateTime.now())
                 .status("COLD")
                 .reason("FCGVYHBJK")
                 .build();
         Appointment appointment1 = appointmentService.saveAppointment(appointment,1L,1L);
         //System.out.println(appointment1);
         Appointment appointment2 =appointmentService.deleteAppointment(1L);
         System.out.println(appointment2);

     }

}
