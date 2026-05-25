package com.hospital.management.service;

import com.hospital.management.model.Patient;
import com.hospital.management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repo;

    public Patient addPatient(Patient p) {
        return repo.save(p);
    }

    public List<Patient> getAllPatients() {
        return repo.findAll();
    }

    public Patient getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deletePatient(Long id) {
        repo.deleteById(id);
    }
}