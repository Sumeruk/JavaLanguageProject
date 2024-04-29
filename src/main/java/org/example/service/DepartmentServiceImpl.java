package org.example.service;

import org.example.entities.Department;
import org.example.repository.DepartmentRepository;
import org.example.repository.DepartmentRepositoryJDBCImpl;
import org.example.repository.PatientRepository;
import org.example.repository.PatientRepositoryJDBCImpl;

import java.util.Arrays;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
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
        departmentRepository = DepartmentRepositoryJDBCImpl.getInstance();
        patientRepository = PatientRepositoryJDBCImpl.getInstance();
    }

    private Department setInfoForNewDepartment(String[] parameters) {
        try {
            int departmentId = Integer.parseInt(parameters[0]);
            String name = parameters[1];
            return new Department(departmentId, name);
        } catch (NumberFormatException nfe) {
            System.out.println("Wrong parameters for department");
        }
        return null;
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.getDepartmentByName(name);
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
        patientRepository.deletePatientsByDepartmentId(id);
    }

    @Override
    public void removeAll() {
        departmentRepository.removeAll();
        patientRepository.removeAll();
    }

    @Override
    public void update(String[] parameters) {
        int id = Integer.parseInt(parameters[0]);
        Department newDepartment = setInfoForNewDepartment(Arrays.copyOfRange(parameters, 1, parameters.length));
        if(newDepartment != null) {
            departmentRepository.update(id, newDepartment);
            patientRepository.updateDepartmentId(id, newDepartment.getId());
        }
    }

    @Override
    public Department getById(int id) {
        return departmentRepository.getById(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }
}
