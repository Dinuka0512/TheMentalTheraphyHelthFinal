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
    private final Session session = FactoryConfig.getInstance().getSession();
    //========

    @Override
    public String generateNewId() {
        session.beginTransaction();
        NativeQuery<Therapist> queree = session.createNativeQuery("SELECT * FROM Therapist GROUP BY therapist_Id DESC LIMIT 1", Therapist.class);
        Therapist therapist = queree.uniqueResult();
        session.getTransaction().commit();

        if(therapist!=null){
            String oldId = therapist.getTherapist_Id(); //T001
            String subId = oldId.substring(2); //001
            int i = Integer.parseInt(subId); //1
            i = i + 1; //2
            return String.format("T%03d",i); //T002
        }
        return "T001";
    }

    @Override
    public ArrayList<Therapist> getAll() {
        session.beginTransaction();
        Query<Therapist> queree = session.createQuery("FROM Therapist", Therapist.class);
        List<Therapist> resultList = queree.getResultList();
        session.getTransaction().commit();
        return (ArrayList<Therapist>) resultList;
    }

    @Override
    public boolean save(Therapist dto) {
        session.beginTransaction();
        session.persist(dto);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Therapist dto) {
        session.beginTransaction();
        session.merge(dto);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        session.beginTransaction();
        session.remove(id);
        session.getTransaction().commit();
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
}
