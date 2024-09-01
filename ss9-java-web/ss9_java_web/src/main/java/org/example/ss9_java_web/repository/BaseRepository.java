package org.example.ss9_java_web.repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/c0324m4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "codegym";

    public BaseRepository() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Kết nối thành công / Connection success");
    }
    public Connection getConnection(){
        return connection;
    }
}
