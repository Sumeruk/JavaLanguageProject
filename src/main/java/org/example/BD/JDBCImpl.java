package org.example.BD;

import java.sql.*;

public class JDBCImpl implements JDBC{
    static String url = "jdbc:postgresql://127.0.0.1:5432/hospital";
    static String username = "postgres";
    static String password = "root";
    private static Connection connection;
    private static JDBC object;

    public static JDBC getInstance(){
        if(object == null){
            object = new JDBCImpl();
        }

        return object;
    }

    private JDBCImpl() {
    }

    public void connection(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password); //соединение с БД
            System.out.println("Соединение с СУБД выполнено.");
        } catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        } catch (ClassNotFoundException clnfe){
            System.out.println("драйвер не найден");
        }

    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException sqle){
            System.out.println("Не удалось выполнить разъединение");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
