package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "Alex0617";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }



}
