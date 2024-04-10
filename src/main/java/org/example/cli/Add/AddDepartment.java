package org.example.cli.Add;

import org.example.cli.Command;
import org.example.service.DepartmentService;
import org.example.service.DepartmentServiceImpl;

import java.util.Scanner;

public class AddDepartment implements Command {
    private Scanner scn = new Scanner(System.in);
    private DepartmentService departmentService;

    public AddDepartment() {
        departmentService = DepartmentServiceImpl.getInstance();
    }

    private String[] setParameters(){
        String[] parameters = new String[2];

        System.out.print("input id: ");
        parameters[0] = scn.nextLine();

        System.out.print("input name: ");
        parameters[1] = scn.nextLine();
        return parameters;
    }

    @Override
    public void execute() {
        departmentService.add(setParameters());
        System.out.println(departmentService.getAll().toString());
    }

    @Override
    public String getCommandName() {
        return "Add department";
    }
}
