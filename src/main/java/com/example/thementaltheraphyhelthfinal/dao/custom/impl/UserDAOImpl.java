package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.UserDAO;
import com.example.thementaltheraphyhelthfinal.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
}
