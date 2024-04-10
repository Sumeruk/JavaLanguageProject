package org.example.cli.Delete;

import org.example.cli.Command;
import org.example.service.PatientService;
import org.example.service.PatientServiceImpl;

import java.util.Scanner;

public class DeletePatientById implements Command {

    Scanner scn = new Scanner(System.in);
    PatientService patientService;

    public DeletePatientById() {
        patientService = PatientServiceImpl.getInstance();
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
        patientService.remove(setId());
    }

    @Override
    public String getCommandName() {
        return "Delete patient by id";
    }
}
