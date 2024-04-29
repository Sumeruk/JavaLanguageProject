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
import java.util.NoSuchElementException;
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
        } catch (NumberFormatException nfe) {
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

    @Override
    public void add(String[] parameters) {
        Patient newPatient = setInfoForNewPatient(parameters);
        if (newPatient != null) {
            patientRepository.add(newPatient);
        }
    }

    private Patient setInfoForNewPatient(String[] parameters) {
        try {
            Patient newPatient = new Patient();
            newPatient.setPatientId(Integer.parseInt(parameters[0]));
            newPatient.setFIO(parameters[1]);
            newPatient.setAge(Short.parseShort(parameters[2]));
            newPatient.setGender(Gender.valueOf(parameters[3]));
            newPatient.setDepartmentId(departmentRepository.getDepartmentByName(parameters[4]).getId());
            return newPatient;
        } catch (IllegalArgumentException iae) {
            System.out.println("Wrong input parameters");
        } catch (NullPointerException npe) {
            System.out.println("Cannot find this department");
        }

        return null;
    }

    @Override
    public void remove(int id) {
        patientRepository.remove(id);
    }

    @Override
    public void removeAll() {
        patientRepository.removeAll();
    }

    @Override
    public void update(String[] parameters) {
        int id = Integer.parseInt(parameters[0]);
        Patient newPatient = setInfoForNewPatient(Arrays.copyOfRange(parameters, 1, parameters.length));
        if (newPatient != null) {
            try {
                patientRepository.update(id, newPatient);
            } catch (NoSuchElementException noElemExc) {
                System.out.println("No patient with this id");
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
