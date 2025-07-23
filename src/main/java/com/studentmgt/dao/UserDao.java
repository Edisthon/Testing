package com.studentmgt.dao;

import com.studentmgt.model.User;
import com.studentmgt.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

public class UserDao {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User getUser(String personId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, personId);
        }
    }

    public String getProvinceName(String personId) {
        User user = getUser(personId);
        if (user != null && user.getVillage() != null) {
            LocationDao locationDao = new LocationDao();
            return locationDao.getProvinceName(user.getVillage().getLocationId());
        }
        return null;
    }

    public boolean authenticateUser(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.createQuery("from User where userName = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
            if (user != null) {
                return BCrypt.checkpw(password, user.getPassword());
            }
            return false;
        }
    }

    public java.util.List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }
}
