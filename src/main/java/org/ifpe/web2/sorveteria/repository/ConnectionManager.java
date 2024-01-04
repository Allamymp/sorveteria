package org.ifpe.web2.sorveteria.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/sorveteriaDB";
    private static final String USER = "sorveteriaAdm";
    private static final String PASSWORD = "123";
    private static Connection conn = null;

    protected static Connection getCurrentConnection() throws SQLException {

        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Driver PostgreSQL não encontrado.", e);
            }
        }

        return conn;
    }
    protected static Connection getNewConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver PostgreSQL não encontrado.", e);
        }
    }
}
