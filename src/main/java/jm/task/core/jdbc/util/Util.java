package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pp113db";
    private static final String USER = "rootroot";
    private static final String PASSWORD = "root";

    private static final Logger log = Logger.getLogger(Util.class.getName());

    private static Connection connection;
    private static SessionFactory sessionFactory;

    static {
       try {
           Configuration configuration = new Configuration();
           configuration.setProperty("hibernate.connection.url", URL);
           configuration.setProperty("hibernate.connection.username", USER);
           configuration.setProperty("hibernate.connection.password", PASSWORD);
           configuration.setProperty("current_session_context_class", "thread");
           configuration.addAnnotatedClass(User.class);
           ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                   .applySettings(configuration
                           .getProperties())
                   .build();
           sessionFactory = configuration.buildSessionFactory(serviceRegistry);
       } catch (Exception ex) {
           log.log(Level.SEVERE, ex.getMessage(), ex);
       }
    }

    private Util() {}

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Connection false" );
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
