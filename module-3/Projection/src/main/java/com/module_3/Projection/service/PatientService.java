package com.module_3.Projection.service;

import com.module_3.Projection.entities.Patient;
import com.module_3.Projection.repositories.PatientRepositories;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepositories patientRepositories;

    @Transactional
    public void test()
    {
        Patient p1 = patientRepositories.findById(1L).orElseThrow();
        Patient p2 = patientRepositories.findById(1L).orElseThrow();
        System.out.println(p1==p2);
        System.out.println(p1 + " "+p2);
    }

}
