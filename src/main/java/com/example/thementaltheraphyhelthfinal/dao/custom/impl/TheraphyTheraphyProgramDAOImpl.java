package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.TheraphyProgramDAO;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TheraphyTheraphyProgramDAOImpl implements TheraphyProgramDAO {
    private Session session = FactoryConfig.getInstance().getSession();

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
        session.close();

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
