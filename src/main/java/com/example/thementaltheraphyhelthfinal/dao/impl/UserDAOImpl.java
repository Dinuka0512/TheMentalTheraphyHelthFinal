package com.example.thementaltheraphyhelthfinal.dao.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.UserDAO;
import com.example.thementaltheraphyhelthfinal.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    ///====
    private Session session = FactoryConfig.getInstance().getSession();
    ///====
    @Override
    public User getUserDetails(String email) {
        session.beginTransaction();
        Query query = session.createNativeQuery("select * from user where email = '" + email +"';");

        return null;
    }
}
