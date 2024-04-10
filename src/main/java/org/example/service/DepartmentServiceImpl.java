package org.example.service;

import org.example.entities.Department;
import org.example.entities.Patient;
import org.example.repository.DepartmentRepository;
import org.example.repository.DepartmentRepositoryImpl;
import org.example.repository.PatientRepository;
import org.example.repository.PatientRepositoryImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DepartmentServiceImpl implements DepartmentService {
    private Scanner scn = new Scanner(System.in);
    private static DepartmentService departmentService;
    private DepartmentRepository departmentRepository;
    private PatientRepository patientRepository;

    public static DepartmentService getInstance() {
        if (departmentService == null) {
            departmentService = new DepartmentServiceImpl();
        }
        return departmentService;
    }

    private DepartmentServiceImpl() {
        departmentRepository = DepartmentRepositoryImpl.getInstance();
        patientRepository = PatientRepositoryImpl.getInstance();
    }

    private Department setInfoForNewDepartment(String[] parameters) {
        try {
            int departmentId = Integer.parseInt(parameters[0]);
            String name = parameters[1];
            return new Department(departmentId, name, 0);
        } catch (Exception e) {
            System.out.println("Wrong parameters for department");
        }
        return null;
    }

    @Override
    public Department getDepartmentByName(String name) {
        return null;
    }

    @Override
    public void add(String[] parameters) {
        Department department = setInfoForNewDepartment(parameters);
        if (department != null) {
            departmentRepository.add(department);
        }
    }

    @Override
    public void remove(int id) {
        departmentRepository.remove(id);
    }

    @Override
    public void removeAll() {
        departmentRepository.removeAll();
    }

    @Override
    public void update(String[] parameters) {
        int id = Integer.parseInt(parameters[0]);
        Department newDepartment = setInfoForNewDepartment(Arrays.copyOfRange(parameters, 1, parameters.length));
        if(newDepartment != null) {
            departmentRepository.update(id, newDepartment);
            patientRepository.getPatientsByDepartmentId(id);
        }
    }

    @Override
    public Department getById(int id) {
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }
}
