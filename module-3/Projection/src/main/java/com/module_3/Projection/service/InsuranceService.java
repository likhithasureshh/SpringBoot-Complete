package com.module_3.Projection.service;

import com.module_3.Projection.entities.Insurance;
import com.module_3.Projection.entities.Patient;
import com.module_3.Projection.repositories.PatientRepositories;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final PatientRepositories patientRepositories;

    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance,Long patientId)
    {
        Patient patient = patientRepositories.findById(patientId).orElseThrow();
        patient.setInsurance(insurance);
        return insurance;
    }

}
