package org.example.service;

import org.example.entities.Department;

public interface DepartmentService extends Service<Department, Integer> {
    Department getDepartmentByName(String name);
}
