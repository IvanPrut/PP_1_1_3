package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    public static Logger log = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        String query = "create table user (id BIGINT auto_increment primary key, name varchar(80) " +
                "not null, lastName varchar(80) not null, age tinyint not null);";
        try (Connection connection1 = Util.getConnection(); Statement statement1 = connection1.createStatement()) {
            statement1.executeUpdate(query);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error creating table", e);
        }
    }

    public void dropUsersTable() {
        String query = "drop table user";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error dropping table", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into user (name, lastName, age) values (?, ?, ?)";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error saving user", e);
        }
    }

    public void removeUserById(long id) {
        String query = "delete from user where id = ?";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error removing user", e);
        }
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
        String query = "delete from user";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error cleaning table", e);
        }
    }
}
