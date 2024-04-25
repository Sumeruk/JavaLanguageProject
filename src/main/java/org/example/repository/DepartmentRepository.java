package org.example.repository;

import org.example.entities.Department;
import org.example.entities.Patient;

import java.util.List;
import java.util.UUID;

public interface DepartmentRepository extends Repository<Department, Integer>{
    Department getDepartmentByName(String name);

}
