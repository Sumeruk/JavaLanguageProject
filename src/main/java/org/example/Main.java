package org.example;

import org.example.BD.JDBC;
import org.example.BD.JDBCImpl;
import org.example.cli.Menu;

public class Main {
    public static void main(String[] args) {

        JDBC jdbc = JDBCImpl.getInstance();
        jdbc.connection();
        Menu.run();
        jdbc.closeConnection();
    }
}