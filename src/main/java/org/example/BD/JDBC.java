package org.example.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface JDBC {

    void connection();
    void closeConnection();
    Connection getConnection();
}
