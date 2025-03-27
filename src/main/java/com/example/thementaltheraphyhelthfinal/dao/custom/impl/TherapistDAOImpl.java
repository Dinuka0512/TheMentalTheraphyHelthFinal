package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO{
    //========
    private final Session session = FactoryConfig.getInstance().getSession();
    //========

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
        return false;
    }

    @Override
    public boolean update(Therapist dto) {
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
    public String generateNewId() {
        return null;
    }

    @Override
    public Therapist search(String id) {
        return null;
    }
}
