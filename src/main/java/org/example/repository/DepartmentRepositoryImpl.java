package org.example.repository;

import org.example.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartmentRepositoryImpl implements DepartmentRepository{

    private static DepartmentRepository object;
    private List<Department> departments;

    public DepartmentRepositoryImpl() {
        departments = new ArrayList<>();
    }

    public static DepartmentRepository getInstance(){
        if (object == null){
            object = new DepartmentRepositoryImpl();
        }
        return object;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        try {
            for (int i = 0; i < departments.size(); i++) {
                if (departments.get(i).getId() == departmentId) {
                    return departments.get(i);
                }
            }
        } catch (NullPointerException npe){
            System.out.println("Cannot find this department");
        }
        return null;
    }

    @Override
    public Department getDepartmentByName(String name) {
        for (Department department : departments) {
            if (department.getName().equals(name)) {
                return department;
            }
        }
        return new Department();
    }

    @Override
    public void deletePatientsFromDepartments() {
        for (int i = 0; i < departments.size(); i++) {
            departments.get(i).setAllPatients(new ArrayList<>());
        }
    }

    @Override
    public void add(Department object) {
        departments.add(object);
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId() == id){
                departments.remove(i);
                return;
            }
        }
    }

    @Override
    public void removeAll() {
        departments.clear();
    }

    @Override
    public void update(int id, Department newObject) {
        int i;
        for (i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId() == id){
                departments.set(i, newObject);
                return;
            }
        }
        if(i == departments.size()){
            System.out.println("No departments with this id");
        }
    }

    @Override
    public Department getById(int id) {
        for (Department department : departments) {
            if (department.getId() == id) {
                return department;
            }
        }
        return null;
    }



    @Override
    public List<Department> getAll() {
        return departments;
    }
}
