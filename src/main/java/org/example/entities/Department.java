package org.example.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Department {
    private int id;
    private String name;
    private int numOfPatients;
    public Department() {
    }
    public Department(int id, String name, int numOfPatients) {
        this.id = id;
        this.name = name;
        this.numOfPatients = numOfPatients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getNumOfPatients() {
        return numOfPatients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumOfPatients(int numOfPatients) {
        this.numOfPatients = numOfPatients;
    }
    @Override
    public String toString() {
        return "\n Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numOfPatients=" + this.numOfPatients +
                "} \n";
    }
}
