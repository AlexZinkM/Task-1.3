package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String create = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGSERIAL PRIMARY KEY, " +
            "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
            "age SMALLINT NOT NULL)";
    private static final String drop = "DROP TABLE IF EXISTS users";

    private static final String truncate = "truncate table users";

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try {
            Session session = HibernateUtil.getFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(create).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = HibernateUtil.getFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(drop).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = HibernateUtil.getFactory().getCurrentSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = HibernateUtil.getFactory().getCurrentSession();
            session.beginTransaction();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> userList = null;
        try {
            session = HibernateUtil.getFactory().getCurrentSession();
            session.beginTransaction();
            userList = session.createQuery("from User")
                    .getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = HibernateUtil.getFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(truncate).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }
}
