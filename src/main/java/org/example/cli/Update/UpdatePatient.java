package org.example.cli.Update;

import org.example.cli.Command;
import org.example.service.PatientService;
import org.example.service.PatientServiceImpl;

import java.util.Scanner;

public class UpdatePatient implements Command {
    private PatientService patientService;
    private Scanner scn = new Scanner(System.in);

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

    public UpdatePatient() {
        patientService = PatientServiceImpl.getInstance();
    }

    private String[] setParameters(){
        String[] parameters = new String[6];
        int i = 0;

        System.out.print("input old id: ");
        parameters[i++] = scn.next();
        scn.nextLine();

        System.out.print("input new id: ");
        parameters[i++] = scn.nextLine();

        System.out.print("input new name: ");
        parameters[i++] = scn.nextLine();

        System.out.print("input new age: ");
        parameters[i++] = scn.next();
        scn.nextLine();

        System.out.print("input new gender: ");
        parameters[i++] = scn.next();
        scn.nextLine();

        System.out.println("input new department: ");
        parameters[i] = scn.next();
        scn.nextLine();
        return parameters;
    }

    @Override
    public void execute() {
        patientService.update(setParameters());
    }

    @Override
    public String getCommandName() {
        return "Update patient";
    }
}
