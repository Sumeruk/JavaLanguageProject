package org.example.repository;

import org.example.entities.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientRepository extends Repository<Patient, Integer>{
    List<Patient> getPatientsByDepartmentId(int departmentId);

    void deletePatientsByDepartmentId(int departmentId);

    void updateDepartmentId(int oldDepId, int newDepId);
}
