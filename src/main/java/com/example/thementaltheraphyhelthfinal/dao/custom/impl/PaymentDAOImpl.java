package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.PaymentDAO;
import com.example.thementaltheraphyhelthfinal.entities.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {

    public int genarateId() {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();

        // Using native SQL query with LIMIT
        String sql = "SELECT * FROM Payment ORDER BY Payment_Id DESC LIMIT 1";
        Query query = session.createNativeQuery(sql, Payment.class);
        Payment payment = (Payment) query.uniqueResult();  // This will return the first result, if any

        if (payment != null) {
            int lastId = payment.getPayment_Id();
            session.getTransaction().commit();
            session.close();
            return lastId + 1;
        } else {
            session.getTransaction().commit();
            session.close();
            return 1;  // Return 1 if no payments exist
        }
    }



    @Override
    public String generateNewId() {
        return null;
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
    public Payment search(String id) {
        return null;
    }
}
