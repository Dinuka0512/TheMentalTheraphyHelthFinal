package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.RegistrationDAO;
import com.example.thementaltheraphyhelthfinal.entities.Registration;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public String generateNewId() {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        NativeQuery<Registration> query = session.createNativeQuery("SELECT * FROM Registration ORDER BY Registration_Id DESC LIMIT 1", Registration.class);
        Registration result = query.getSingleResult();
        if(result!=null){
            String lastId = result.getRegistration_Id(); //R001
            String subId = lastId.substring(1); //001
            int i = Integer.parseInt(subId);
            i = i + 1;
            return String.format("R%03d", i);
        }

        return "R001";
    }

    @Override
    public boolean save(Registration dto) {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        session.persist(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Registration> getAll() {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        Query<Registration> query = session.createQuery("FROM Registration", Registration.class);
        List<Registration> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (ArrayList<Registration>) resultList;
    }

    @Override
    public boolean update(Registration dto) {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        session.merge(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        Registration registration = session.get(Registration.class, id);
        if(registration!=null){
            session.remove(registration);
        }
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public Registration search(String id) {
        return null;
    }
}
