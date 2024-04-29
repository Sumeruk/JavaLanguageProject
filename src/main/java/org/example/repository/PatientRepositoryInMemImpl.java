package org.example.repository;

import org.example.entities.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PatientRepositoryInMemImpl implements PatientRepository {
    private static PatientRepository object;
    private List<Patient> patients;

    public PatientRepositoryInMemImpl() {
        patients = new ArrayList<>();
    }

    public static PatientRepository getInstance() {
        if (object == null) {
            object = new PatientRepositoryInMemImpl();
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
    public void update(int id, Patient patient) {
        int i = 0;
        for (; i < patients.size(); i++){
            if(patients.get(i).getPatientId() == id){
                patients.set(i, patient);
            }
        }
        if (i == patients.size()){
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updateDepartmentId(int oldDepId, int newDepId){
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getDepartmentId() == oldDepId){
                patients.get(i).setPatientId(newDepId);
            }
        }
    }

    @Override
    public void deletePatientsByDepartmentId(int departmentId) {
        for (int i = 0; i < patients.size(); i++) {
            if(patients.get(i).getDepartmentId() == departmentId){
                patients.remove(i);
                i--;
            }
        }
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
