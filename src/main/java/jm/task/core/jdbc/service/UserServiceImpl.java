package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    public void createUsersTable() throws SQLException {

        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
        ud.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
        ud.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
        ud.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
        ud.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
        return ud.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
        ud.cleanUsersTable();
    }
}
