package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.RegistrationDAO;
import com.example.thementaltheraphyhelthfinal.entities.Registration;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public String generateNewId() {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        NativeQuery<Registration> queree = session.createNativeQuery("SELECT * FROM Registration GROUP BY Registration_Id DESC LIMIT 1", Registration.class);
        try{
            Registration registration = queree.getSingleResult();
            if(registration != null){
                String lastId = registration.getRegistration_Id(); //R001
                String subId = lastId.substring(1); //001
                int i = Integer.parseInt(subId); //1
                i = i + 1;
                return String.format("R%03d",i);
            }
        }catch (Exception e){
            session.getTransaction().rollback();
        }
        session.close();
        return "R001";
    }

    @Override
    public ArrayList<Registration> getAll() {
        return null;
    }

    @Override
    public boolean save(Registration dto) {
        return false;
    }

    @Override
    public boolean update(Registration dto) {
        return false;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Registration search(String id) {
        return null;
    }
}
