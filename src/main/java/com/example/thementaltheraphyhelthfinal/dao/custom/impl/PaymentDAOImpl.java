package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.PaymentDAO;
import com.example.thementaltheraphyhelthfinal.entities.Payment;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String generateNewId() {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();

        NativeQuery<Payment> query = session.createNativeQuery("SELECT * FROM Payment ORDER BY Payment_Id DESC LIMIT 1", Payment.class);
        List<Payment> resultList = query.getResultList();

        if(!resultList.isEmpty() ){
            String lastId = resultList.getFirst().getPayment_Id(); //P001
            String suubId = lastId.substring(1); //001
            int i = Integer.parseInt(suubId); //1
            i = i + 1;
            return String.format("P%03d",i);
        }

        return "P001";
    }

    @Override
    public boolean save(Payment dto) {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        session.persist(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Payment> getAll() {
        return null;
    }

    @Override
    public boolean update(Payment dto) {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        session.merge(dto);
        session.getTransaction().commit();
        session.close();
        return true;
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
    public Payment search(String id) {
        return null;
    }

    @Override
    public Payment getPaymenDto(String session_Id) {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        NativeQuery<Payment> query = session.createNativeQuery("SELECT * FROM Payment WHERE session_Id = :id", Payment.class);
        query.setParameter("id", session_Id);
        Payment result = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
