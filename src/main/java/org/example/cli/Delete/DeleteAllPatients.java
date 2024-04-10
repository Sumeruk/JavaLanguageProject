package org.example.cli.Delete;

import org.example.cli.Command;
import org.example.repository.PatientRepositoryImpl;
import org.example.service.DepartmentServiceImpl;
import org.example.service.PatientService;
import org.example.service.PatientServiceImpl;

public class DeleteAllPatients implements Command {
    private PatientService patientService;

    public DeleteAllPatients() {
        this.patientService = PatientServiceImpl.getInstance();
    }

    @Override
    public void execute() {
        patientService.removeAll();
        System.out.println("Current list of patients: " + patientService.getAll());
    }

    @Override
    public String getCommandName() {
        return "Delete all patients";
    }
}
