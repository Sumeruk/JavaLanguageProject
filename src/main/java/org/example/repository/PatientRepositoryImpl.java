package org.example.repository;

import org.example.entities.Department;
import org.example.entities.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PatientRepositoryImpl implements PatientRepository {
    private static PatientRepository object;
    private DepartmentRepository departmentRepository = DepartmentRepositoryImpl.getInstance();
    private List<Patient> patients;
    private Department tmpDepartment;

    public PatientRepositoryImpl() {
        patients = new ArrayList<>();
    }

    public static PatientRepository getInstance() {
        if (object == null) {
            object = new PatientRepositoryImpl();
        }
        return object;
    }

    @Override
    public List<Patient> getPatientsByDepartmentId(int departmentId) {
        List<Patient> patientsWithDepartmentId = new ArrayList<>();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getDepartmentId() == departmentId) {
                patientsWithDepartmentId.add(patients.get(i));
            }
        }
        return patientsWithDepartmentId;
    }

    @Override
    public void deletePatientsWithDepartmentId(int departmentId) {

    }

    @Override
    public void add(Patient object) {
        patients.add(object);
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId() == id) {
                patients.remove(i);
                return;
            }
        }
    }

    @Override
    public void removeAll() {
        patients.clear();
    }

    @Override
    public void update(int id, Patient newObject) {
        int i;
        for (i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId() == id) {
                patients.set(i, newObject);
                Department newDep = departmentRepository.getDepartmentById(newObject.getDepartmentId());
                newDep.setPatient(newObject);
                return;
            }
        }

        if (i == patients.size()) {
            System.out.println("No patient with this id");
        }

    }

    @Override
    public Patient getById(int id) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId() == id) {
                return patients.get(i);
            }
        }

        return null;
    }

    @Override
    public List<Patient> getAll() {
        return patients;
    }
}
