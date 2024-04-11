package org.example.cli;

import org.example.cli.Add.AddDepartment;
import org.example.cli.Add.AddPatient;
import org.example.cli.Delete.DeleteAllDepartments;
import org.example.cli.Delete.DeleteAllPatients;
import org.example.cli.Delete.DeleteDepartmentById;
import org.example.cli.Delete.DeletePatientById;
import org.example.cli.Get.GetAllDepartments;
import org.example.cli.Get.GetAllPatients;
import org.example.cli.Get.GetDepartmentById;
import org.example.cli.Get.GetPatientById;
import org.example.cli.Update.UpdatePatient;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scn = new Scanner(System.in);
    private static Command[] commands = new Command[]{
            new AddPatient(),
            new AddDepartment(),
            new GetAllDepartments(),
            new GetDepartmentById(),
            new GetAllPatients(),
            new GetPatientById(),
            new UpdatePatient(),
            new DeleteAllPatients(),
            new DeletePatientById(),
            new DeleteAllDepartments(),
            new DeleteDepartmentById()
    };

    public static void run(){
        while (true){
            System.out.println();
            for (int i = 1; i <= commands.length; i++) {
                System.out.println(i + " " + commands[i - 1].getCommandName());
            }
            int inputCommand = 0;
            try {
                inputCommand = scn.nextInt();
            } catch (InputMismatchException ime){
                System.out.println("wrong command");
                continue;
            }

            if(inputCommand == -1){
                System.out.println("Program execute with exit code 130");
                return;
            }

            if(inputCommand > commands.length){
                System.out.println("Wrong command");
                continue;
            }

            commands[inputCommand - 1].execute();

        }
    }
}
