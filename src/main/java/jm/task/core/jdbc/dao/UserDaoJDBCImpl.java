package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {


    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS users (id BIGSERIAL PRIMARY KEY, name VARCHAR(32) NOT NULL," +
                " lastName VARCHAR(32) NOT NULL, age SMALLINT NOT NULL)";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(create);
        } catch (SQLException e) {
            throw e;
            //System.out.println("Не удалось создать таблицу");

        }
    }


    public void dropUsersTable() throws SQLException {
        String drop = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);
        } catch (SQLException e) {
            System.out.println("Нет такой таблицы");
        }

    }


    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String insert = "INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)";
        try (Connection connection = Util.getConnection(); PreparedStatement pr_statement = connection.prepareStatement(insert)) {
            pr_statement.setString(1, name);
            pr_statement.setString(2, lastName);
            pr_statement.setInt(3, age);
            pr_statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }


    public void removeUserById(long id) throws SQLException {
        String get = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection(); PreparedStatement pr_statement = connection.prepareStatement(get)) {
            pr_statement.setLong(1, id);
            pr_statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String getAll = "SELECT id, name, lastName, age FROM users";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
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
            throw e;
        }
    }


    public void cleanUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            String delete = "TRUNCATE TABLE users";
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            throw e;
        }
    }
}
