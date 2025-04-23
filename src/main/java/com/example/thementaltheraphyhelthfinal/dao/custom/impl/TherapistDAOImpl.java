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
        try{
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
        }finally {
            session.getTransaction().commit();
            session.close();
        }
        return "T001";
    }

    @Override
    public ArrayList<Therapist> getAll() {
        Session session = factoryConfig.getSession();
        try{
            session.beginTransaction();
            Query<Therapist> queree = session.createQuery("FROM Therapist", Therapist.class);
            List<Therapist> resultList = queree.getResultList();
            return (ArrayList<Therapist>) resultList;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean save(Therapist dto) {
        Session session = factoryConfig.getSession();
        try{
            session.beginTransaction();
            session.persist(dto);
        }finally {
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }

    @Override
    public boolean update(Therapist dto) {
        Session session = factoryConfig.getSession();
        try{
            session.beginTransaction();
            session.merge(dto);
        }finally{
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }

    @Override
    public boolean isValidToSave(String email) {
        Session session = factoryConfig.getSession();
        try{
            session.beginTransaction();
            Query query = session.createQuery("FROM Therapist WHERE email = :email", Therapist.class);
            query.setParameter("email",email);

            List resultList = query.getResultList();
            return (resultList.isEmpty());
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean isValidToUpdate(String email, String id) {
        Session session = factoryConfig.getSession();
        try {
            session.beginTransaction();
            Query<Therapist> query = session.createNativeQuery("SELECT * FROM Therapist WHERE email = :email AND therapist_Id != :id", Therapist.class);
            query.setParameter("email",email);
            query.setParameter("id",id);

            List<Therapist> resultList = query.getResultList();
            return (resultList.isEmpty());
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfig.factoryCongig.getSession();
        try{
            session.beginTransaction();
            Therapist therapist = session.get(Therapist.class, id);
            if(therapist!=null){
                session.remove(therapist);
            }
        }finally {
            session.getTransaction().commit();
            session.close();
        }
        return true;
    }

    @Override
    public boolean delete(Therapist therapist) {
        return false;
    }

    @Override
    public ArrayList<String> loadtherapist(String programId) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();
            NativeQuery<Therapist> query = session.createNativeQuery("SELECT * from Therapist WHERE program_program_Id = :programId", Therapist.class);
            query.setParameter("programId", programId);
            List<Therapist> list = query.getResultList();
            ArrayList<String> arrayList = new ArrayList<>();
            if(list!=null){
                for(Therapist therapist : list){
                    arrayList.add(therapist.getTherapist_Id());
                }
                return arrayList;
            }
        }finally {
            session.getTransaction().commit();
            session.close();
        }
        return null;
    }

    @Override
    public Therapist getTherapistDetails(String selectedItem) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();
            Therapist therapist = session.get(Therapist.class, selectedItem);
            return therapist;
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
    public Therapist search(String id) {
        return null;
    }
}
