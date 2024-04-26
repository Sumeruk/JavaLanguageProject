package org.example.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Department {
    private int id;
    private String name;
    public Department() {
    }
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "} \n";
    }
}
