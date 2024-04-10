package org.example.repository;

import org.example.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentRepository extends Repository<Department, Integer>{
    Department getDepartmentById(int departmentId);
    Department getDepartmentByName(String name);
    void deletePatientsFromDepartments();
}
