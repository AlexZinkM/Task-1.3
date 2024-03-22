package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private static final String create = "CREATE TABLE IF NOT EXISTS users (id BIGSERIAL PRIMARY KEY, " +
            "name VARCHAR(32) NOT NULL," +
            " lastName VARCHAR(32) NOT NULL, age SMALLINT CHECK (age >= 0 AND age <= 255) NOT NULL)";
    private static final String drop = "DROP TABLE IF EXISTS users";
    private static final String insert = "INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)";
    private static final String get = "DELETE FROM users WHERE id = ?";
    private static final String getAll = "SELECT * FROM users";
    private static final String delete = "TRUNCATE TABLE users";


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);
        } catch (SQLException e) {
            System.out.println("Нет такой таблицы");
            e.printStackTrace();
        }

    }


    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement pr_statement = connection.prepareStatement(insert)) {
            pr_statement.setString(1, name);
            pr_statement.setString(2, lastName);
            pr_statement.setInt(3, age);
            pr_statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement pr_statement = connection.prepareStatement(get)) {
            pr_statement.setLong(1, id);
            pr_statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);

            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
