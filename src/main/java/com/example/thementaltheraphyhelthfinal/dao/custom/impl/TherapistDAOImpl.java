package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.TherapistDAO;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    //========
    private final FactoryConfig factoryConfig = FactoryConfig.getInstance();
    //========

    @Override
    public String generateNewId() {
        Session session = factoryConfig.getSession();
        session.beginTransaction();
        NativeQuery<Therapist> queree = session.createNativeQuery("SELECT * FROM Therapist GROUP BY therapist_Id DESC LIMIT 1", Therapist.class);
        Therapist therapist = queree.uniqueResult();

        if(therapist!=null){
            String oldId = therapist.getTherapist_Id(); //T001
            String subId = oldId.substring(2); //001
            int i = Integer.parseInt(subId); //1
            i = i + 1; //2
            return String.format("T%03d",i); //T002
        }
        session.getTransaction().commit();
        session.close();
        return "T001";
    }

    @Override
    public ArrayList<Therapist> getAll() {
        Session session = factoryConfig.getSession();
        session.beginTransaction();
        Query<Therapist> queree = session.createQuery("FROM Therapist", Therapist.class);
        List<Therapist> resultList = queree.getResultList();
        session.getTransaction().commit();
        session.close();
        return (ArrayList<Therapist>) resultList;
    }

    @Override
    public boolean save(Therapist dto) {
        Session session = factoryConfig.getSession();
        session.beginTransaction();
        session.persist(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Therapist dto) {
        Session session = factoryConfig.getSession();
        session.beginTransaction();
        session.merge(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean isValidToSave(String email) {
        Session session = factoryConfig.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Therapist WHERE email = :email", Therapist.class);
        query.setParameter("email",email);

        List resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (resultList.isEmpty());
    }

    @Override
    public boolean isValidToUpdate(String email, String id) {
        Session session = factoryConfig.getSession();
        session.beginTransaction();
        Query<Therapist> query = session.createNativeQuery("SELECT * FROM Therapist WHERE email = :email AND therapist_Id != :id", Therapist.class);
        query.setParameter("email",email);
        query.setParameter("id",id);

        List<Therapist> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (resultList.isEmpty());
    }

    @Override
    public boolean delete(Therapist therapist) {
        Session session = factoryConfig.getSession();
        session.beginTransaction();
        session.remove(therapist);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) {
        return false;
    }

    @Override
    public Therapist search(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
