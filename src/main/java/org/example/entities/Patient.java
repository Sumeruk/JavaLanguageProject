package org.example.entities;

import org.example.Help.Gender;

import java.util.UUID;

public class Patient {
    private int patientId;
    private String FIO;
    private int age;
    private Gender gender;
    private int departmentId;

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }
    @Override
    public String toString() {
        return "\n Patient{" +
                "\n id = " + patientId +
                ", FIO = '" + FIO + '\'' +
                ", age = " + age +
                ", gender = " + gender +
                ", departmentId = " + departmentId +
                "} \n";
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFIO() {
        return FIO;
    }

    public Patient(int patientId, String FIO, int age, Gender gender, int departmentId) {
        this.patientId = patientId;
        this.FIO = FIO;
        this.age = age;
        this.gender = gender;
        this.departmentId = departmentId;
    }

    public Patient(String FIO, short age, Gender gender) {
        this.FIO = FIO;
        this.age = age;
        this.gender = gender;
    }

    public Patient() {
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

}
