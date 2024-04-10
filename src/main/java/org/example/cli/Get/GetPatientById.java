package org.example.cli.Get;

import org.example.cli.Command;
import org.example.service.PatientService;
import org.example.service.PatientServiceImpl;

import java.util.Scanner;

public class GetPatientById implements Command {
    Scanner scn = new Scanner(System.in);

    PatientService patientService;

    public GetPatientById() {
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
        patientService.getById(setId());
    }

    @Override
    public String getCommandName() {
        return "Get patient by id";
    }
}
