package org.example.cli.Update;

import org.example.cli.Command;
import org.example.service.DepartmentService;
import org.example.service.DepartmentServiceImpl;

import java.util.Scanner;

public class UpdateDepartment implements Command {

    private Scanner scn = new Scanner(System.in);
    private DepartmentService departmentService;

    public UpdateDepartment() {
        departmentService = DepartmentServiceImpl.getInstance();
    }

    private String[] setParameters(){
        String[] parameters = new String[3];

        System.out.print("input old id: ");
        parameters[0] = scn.nextLine();

        System.out.print("input new id: ");
        parameters[1] = scn.nextLine();

        System.out.print("input new name: ");
        parameters[2] = scn.nextLine();
        return parameters;
    }
    @Override
    public void execute() {
        departmentService.update(setParameters());
    }

    @Override
    public String getCommandName() {
        return "Update Department";
    }
}
