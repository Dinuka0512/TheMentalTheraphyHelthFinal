package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.TheraphyProgramDAO;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TheraphyProgramDAOImpl implements TheraphyProgramDAO {
    private final Session session = FactoryConfig.getInstance().getSession();

    @Override
    public ArrayList<TherapyProgram> searchFromTable(String name) {
        ArrayList<TherapyProgram> therapyPrograms = new ArrayList<>();
        session.beginTransaction();

        NativeQuery<TherapyProgram> queree = session.createNativeQuery("SELECT * FROM TherapyProgram WHERE name = :name", TherapyProgram.class);
        queree.setParameter("name", name);
        List<TherapyProgram> results = queree.getResultList();

        for (TherapyProgram therapyProgram : results){
            therapyPrograms.add(therapyProgram);
        }

        if(results.isEmpty()){
            NativeQuery<TherapyProgram> queree1 = session.createNativeQuery("SELECT * FROM TherapyProgram th WHERE th.name LIKE :name", TherapyProgram.class);
            queree1.setParameter("name", "%" + name + "%");
            List<TherapyProgram> results1 = queree1.getResultList();

            for (TherapyProgram therapyProgram : results1){
                therapyPrograms.add(therapyProgram);
            }

            if(results1.isEmpty()){
                NativeQuery<TherapyProgram> queree2 = session.createNativeQuery("SELECT * FROM TherapyProgram th WHERE th.name LIKE :name", TherapyProgram.class);
                queree2.setParameter("name", name + "%");
                List<TherapyProgram> results2 = queree2.getResultList();

                for (TherapyProgram therapyProgram : results2){
                    therapyPrograms.add(therapyProgram);
                }

                if(results2.isEmpty()){
                    session.getTransaction().rollback();
                    return null;
                }
            }
        }
        session.getTransaction().commit();
        return therapyPrograms;
    }

    @Override
    public ArrayList<TherapyProgram> getAll(){
        ArrayList<TherapyProgram> therapyPrograms = new ArrayList<>();

        session.beginTransaction();
        Query<TherapyProgram> queree = session.createQuery("FROM TherapyProgram", TherapyProgram.class);
        List<TherapyProgram> resultList = queree.getResultList();

        for(TherapyProgram therapyProgram : resultList){
            therapyPrograms.add(therapyProgram);
        }

        session.getTransaction().commit();

        return therapyPrograms;
    }

    @Override
    public boolean save(TherapyProgram dto){
        return false;
    }

    @Override
    public boolean update(TherapyProgram dto){
        return false;
    }

    @Override
    public boolean exist(String id){
        return false;
    }

    @Override
    public boolean delete(String id){
        return false;
    }

    @Override
    public String generateNewId(){
        return null;
    }

    @Override
    public TherapyProgram search(String id){
        return null;
    }
}
