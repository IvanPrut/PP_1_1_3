package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pp113DB";
    private static final String USER = "rootroot";
    private static final String PASSWORD = "root";

    private static Logger log = Logger.getLogger(Util.class.getName());

    private static Connection connection;

    private Util() {}

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            log.log(Level.INFO, "Connection established" );
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Connection false" );
        }
        return connection;
    }
}
