package org.example.service;

import org.example.Help.Gender;
import org.example.entities.Department;
import org.example.entities.Patient;
import org.example.repository.DepartmentRepository;
import org.example.repository.DepartmentRepositoryImpl;
import org.example.repository.PatientRepository;
import org.example.repository.PatientRepositoryImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PatientServiceImpl implements PatientService {
    private static PatientService patientService;
    private PatientRepository patientRepository;
    private DepartmentRepository departmentRepository;
    private Patient patient;

    private Scanner scn = new Scanner(System.in);

    private int setId() {
        try {
            return scn.nextInt();
        } catch (NumberFormatException nfe){
            System.out.println("Impossible id");
            System.out.println("Write id again");
        }

        return setId();
    }

    public static PatientService getInstance() {
        if (patientService == null) {
            patientService = new PatientServiceImpl();
        }
        return patientService;

    }

    public PatientServiceImpl() {
        patientRepository = PatientRepositoryImpl.getInstance();
        departmentRepository = DepartmentRepositoryImpl.getInstance();
    }

    private Patient createNewPatient(int patientId, String FIO, int age, String gender, String departmentName) {
        int departmentId = 0;
        try {
            departmentId = departmentRepository.getDepartmentByName(departmentName).getId();
        } catch (NullPointerException npe) {
            System.out.println("Can't find department with name " + departmentName);
        }
        return new Patient(patientId, FIO, age, Gender.valueOf(gender), departmentId);
    }

    @Override
    public void add(String[] parameters) {
        Patient newPatient = setInfoForNewPatient(parameters);
        if(newPatient != null) {
            try {
                patientRepository.add(newPatient);
                departmentRepository.getDepartmentById(newPatient.getDepartmentId()).setPatient(newPatient);
            } catch (NullPointerException npe){
                System.out.println("Cannot find this department");
            }
        }
    }

    private Patient setInfoForNewPatient(String[] parameters) {
        try {
            int patientId = Integer.parseInt(parameters[0]);
            String FIO = parameters[1];
            int age = Integer.parseInt(parameters[2]);
            String gender = parameters[3];
            String departmentName = parameters[4];
            return createNewPatient(patientId, FIO, age, gender, departmentName);
        } catch (IllegalArgumentException iae){
            System.out.println("Wrong input parameters");
        }

        return null;
    }

    @Override
    public void remove(int id) {
        List<Patient> patients = departmentRepository.getDepartmentById(
                patientRepository.getById(id).getDepartmentId()).getPatients();

        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId() == id){
                patients.remove(i);
                break;
            }
        }

        departmentRepository.getDepartmentById(
                patientRepository.getById(id).getDepartmentId()).setAllPatients(patients);

        patientRepository.remove(id);
    }

    @Override
    public void removeAll() {
        patientRepository.removeAll();
        departmentRepository.deletePatientsFromDepartments();
    }

    @Override
    public void update(String[] parameters) {
        int id = Integer.parseInt(parameters[0]);
        Patient newPatient = setInfoForNewPatient(Arrays.copyOfRange(parameters, 1, parameters.length));
        if(newPatient != null) {
            try{
                patientRepository.update(id, newPatient);
                departmentRepository.getDepartmentById(newPatient.getDepartmentId()).updatePatient(id, newPatient);
            } catch (NullPointerException npe){
                System.out.println("No department with this name");
            }

        }

    }

    @Override
    public Patient getById(int id) {
        return patientRepository.getById(id);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.getAll();
    }
}
