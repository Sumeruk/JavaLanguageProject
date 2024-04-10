package org.example.cli.Get;

import org.example.cli.Command;
import org.example.service.PatientService;
import org.example.service.PatientServiceImpl;

public class GetAllPatients implements Command {
    private PatientService patientService;

    public GetAllPatients() {
        patientService = PatientServiceImpl.getInstance();
    }

    @Override
    public void execute() {
        System.out.println(patientService.getAll());
    }

    @Override
    public String getCommandName() {
        return "Get all patients";
    }
}
