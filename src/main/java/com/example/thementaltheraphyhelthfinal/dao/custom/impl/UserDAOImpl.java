package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.UserDAO;
import com.example.thementaltheraphyhelthfinal.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    ///====
    private Session session = FactoryConfig.getInstance().getSession();
    ///====
    @Override
    public User getUserDetails(String email) {
        User user = null;
        session.beginTransaction();

        Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email" , email);
        user = query.uniqueResult();

        session.getTransaction().commit();
        return user;
    }

    @Override
    public boolean isUniqueEmail(String email) {
        session.beginTransaction();
        Query<User> query = session.createQuery("FROM User where email = :email", User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();
        session.getTransaction().commit();
        return (user == null)? true : false;
    }

    @Override
    public ArrayList<User> getAll() {
        session.beginTransaction();

        Query<User> queree = session.createQuery("FROM User", User.class);
        List<User> results = queree.getResultList();

        session.getTransaction().commit();
        return (ArrayList<User>) results;
    }

    @Override
    public boolean save(User dto) {
        session.beginTransaction();
        session.persist(dto);
        session.getTransaction().commit();
        return true;
    }

    public boolean delete(int id) {
        session.beginTransaction();

        Query query = session.createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();

        session.getTransaction().commit();
        return (result == 1)? true : false;
    }

    @Override
    public boolean update(User dto) {
        return false;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public String generateNewId() {
        return null;
    }

    @Override
    public User search(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return true;
    }
}
