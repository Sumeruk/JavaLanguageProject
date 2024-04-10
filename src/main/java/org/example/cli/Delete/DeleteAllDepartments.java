package org.example.cli.Delete;

import org.example.cli.Command;
import org.example.service.DepartmentService;
import org.example.service.DepartmentServiceImpl;

public class DeleteAllDepartments implements Command {
    DepartmentService departmentService ;

    public DeleteAllDepartments() {
        departmentService = DepartmentServiceImpl.getInstance();
    }

    @Override
    public void execute() {
        departmentService.removeAll();
        System.out.println("Current list of departments: " + departmentService.getAll());
    }

    @Override
    public String getCommandName() {
        return "Delete all departments";
    }
}
