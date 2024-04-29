package org.example.cli.Add;

import org.example.cli.Command;
import org.example.service.PatientService;
import org.example.service.PatientServiceImpl;

import java.util.Scanner;

public class AddPatient implements Command {
    private final Scanner scn = new Scanner(System.in);
    PatientService patientService;

    public AddPatient() {
        patientService = PatientServiceImpl.getInstance();
    }

    private String[] setParameters(){
        String[] parameters = new String[5];

        System.out.print("input id: ");
        parameters[0] = scn.next();
        scn.nextLine();

        System.out.print("input name: ");
        parameters[1] = scn.nextLine();

        System.out.print("input age: ");
        parameters[2] = scn.next();
        scn.nextLine();

        System.out.print("input gender: ");
        parameters[3] = scn.next();
        scn.nextLine();

        System.out.println("input department name: ");
        parameters[4] = scn.next();
        scn.nextLine();
        return parameters;
    }

    @Override
    public void execute() {
        patientService.add(setParameters());
    }

    @Override
    public String getCommandName() {
        return "Add patient";
    }
}
