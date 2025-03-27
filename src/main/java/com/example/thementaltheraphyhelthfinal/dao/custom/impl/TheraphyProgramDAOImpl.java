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
    public String generateNewId(){
        session.beginTransaction();
        NativeQuery<TherapyProgram> queree = session.createNativeQuery("SELECT * FROM TherapyProgram GROUP BY program_Id DESC LIMIT 1", TherapyProgram.class);
        TherapyProgram therapyProgram = queree.uniqueResult();
        session.getTransaction().commit();

        if(therapyProgram != null){
            String id = therapyProgram.getProgram_Id(); //MT001
            String subString = id.substring(2); //001
            int i =  Integer.parseInt(subString); //1
            int newIndex = i + 1;
            return String.format("MT%02d",newIndex);
        }else {
            return "MT001";
        }
    }

    @Override
    public boolean delete(String id){
        session.beginTransaction();
        System.out.println(id);
        Query query = session.createQuery("DELETE FROM TherapyProgram WHERE program_Id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean save(TherapyProgram dto){
        session.beginTransaction();
        session.persist(dto);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(TherapyProgram dto){
        session.beginTransaction();
        session.merge(dto.getProgram_Id(), dto);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean exist(String id){
        return false;
    }

    @Override
    public TherapyProgram search(String id){
        return null;
    }
}
