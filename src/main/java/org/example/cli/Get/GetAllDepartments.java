package org.example.cli.Get;

import org.example.cli.Command;
import org.example.service.DepartmentService;
import org.example.service.DepartmentServiceImpl;

public class GetAllDepartments implements Command {
    DepartmentService departmentService;

    public GetAllDepartments() {
        departmentService = DepartmentServiceImpl.getInstance();
    }

    @Override
    public void execute() {

        System.out.println(departmentService.getAll());
    }

    @Override
    public String getCommandName() {
        return "Get all departments";
    }
}
