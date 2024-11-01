package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public UserDaoJDBCImpl() {
        // По условию задачи класс dao должен иметь конструктор пустой/по умолчанию
    }

    public void createUsersTable() {
        String query = "create table if not exists user (id BIGINT auto_increment primary key, name varchar(80) " +
                "not null, lastName varchar(80) not null, age tinyint not null);";
        try (Connection connection1 = Util.getConnection(); Statement statement1 = connection1.createStatement()) {
            statement1.executeUpdate(query);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error creating table", e);
        }
    }

    public void dropUsersTable() {
        String query = "drop table if exists user";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error dropping table", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into user (name, lastName, age) values (?, ?, ?)";
        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error saving user", e);
        }
    }

    public void removeUserById(long id) {
        String query = "delete from user where id = ?";
        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error removing user", e);
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String query = "select id, name, lastName, age from user";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                result.add(user);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Error getting all users", e);
        }
        return result;
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
