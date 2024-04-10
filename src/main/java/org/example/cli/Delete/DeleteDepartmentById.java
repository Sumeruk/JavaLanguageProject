package org.example.cli.Delete;

import org.example.cli.Command;
import org.example.service.DepartmentService;
import org.example.service.DepartmentServiceImpl;

import java.util.Scanner;

public class DeleteDepartmentById implements Command {

    DepartmentService departmentService;
    Scanner scn = new Scanner(System.in);

    public DeleteDepartmentById() {
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
        departmentService.remove(setId());
    }

    @Override
    public String getCommandName() {
        return "Delete department by id";
    }
}
