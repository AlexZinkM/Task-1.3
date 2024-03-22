package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userServ = new UserServiceImpl();

        userServ.dropUsersTable();
        userServ.createUsersTable();


        userServ.saveUser("Name1", "LastName1", (byte) 20);
        userServ.saveUser("Name2", "LastName2", (byte) 25);
        userServ.saveUser("Name3", "LastName3", (byte) 31);
        userServ.saveUser("Name4", "LastName4", (byte) 38);

        userServ.removeUserById(1);
        userServ.getAllUsers();
        userServ.cleanUsersTable();
        userServ.dropUsersTable();
    }
}