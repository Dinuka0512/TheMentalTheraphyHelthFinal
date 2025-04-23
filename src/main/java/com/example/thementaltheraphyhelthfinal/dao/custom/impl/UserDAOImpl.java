package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.UserDAO;
import com.example.thementaltheraphyhelthfinal.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    @Override
    public User getUserDetails(String email) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            User user = null;
            session.beginTransaction();

            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email" , email);
            user = query.uniqueResult();

            return user;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean isUniqueEmail(String email) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User where email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            return user == null;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public ArrayList<User> getAll() {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();
            Query<User> queree = session.createQuery("FROM User", User.class);
            List<User> results = queree.getResultList();
            return (ArrayList<User>) results;

        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean save(User dto) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();
            session.persist(dto);
            return true;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public boolean delete(int id) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();

            User user = session.get(User.class, id);
            if(user!=null){
                session.remove(user);
            }
            return true;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean isUniqueEmailForUpdate(String email, int id) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();

            Query<User> query = session.createQuery("FROM User WHERE email = :email AND user_Id != :id", User.class);
            query.setParameter("email",email);
            query.setParameter("id", id);

            User user = query.uniqueResult();
            return user == null;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean update(User dto) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();
            session.merge(dto);
            return true;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
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
