package org.example.cli.Get;

import org.example.cli.Command;
import org.example.service.DepartmentService;
import org.example.service.DepartmentServiceImpl;

import java.util.Scanner;

public class GetDepartmentById implements Command {
    private Scanner scn = new Scanner(System.in);
    private DepartmentService departmentService;

    public GetDepartmentById() {
        departmentService = DepartmentServiceImpl.getInstance();
    }

    private int setId(){
        try {
            System.out.print("id: ");
            return scn.nextInt();
        } catch (NumberFormatException nfe){
            System.out.println("Impossible id");
            System.out.println("Write id again");
        }

        return setId();
    }

    @Override
    public void execute() {
        System.out.println(departmentService.getById(setId()));
    }

    @Override
    public String getCommandName() {
        return "Get department by id";
    }
}
