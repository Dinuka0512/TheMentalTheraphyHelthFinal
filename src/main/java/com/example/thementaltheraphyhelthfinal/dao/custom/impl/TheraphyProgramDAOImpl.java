package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.TheraphyProgramDAO;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.boot.cfgxml.spi.CfgXmlAccessService;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TheraphyProgramDAOImpl implements TheraphyProgramDAO {
    @Override
    public ArrayList<TherapyProgram> searchFromTable(String name) {
        Session session = FactoryConfig.getInstance().getSession();
        try {
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
            return therapyPrograms;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public TherapyProgram getDetails(String selectedItem) {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();
            TherapyProgram therapyProgram = session.get(TherapyProgram.class, selectedItem);
            return therapyProgram;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public ArrayList<TherapyProgram> getAll(){
        Session session = FactoryConfig.getInstance().getSession();
        try {
            ArrayList<TherapyProgram> therapyPrograms = new ArrayList<>();

            session.beginTransaction();
            Query<TherapyProgram> queree = session.createQuery("FROM TherapyProgram", TherapyProgram.class);
            List<TherapyProgram> resultList = queree.getResultList();

            for(TherapyProgram therapyProgram : resultList){
                therapyPrograms.add(therapyProgram);
            }
            return therapyPrograms;

        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public String generateNewId(){
        Session session =FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            NativeQuery<TherapyProgram> queree = session.createNativeQuery("SELECT * FROM TherapyProgram GROUP BY program_Id DESC LIMIT 1", TherapyProgram.class);
            TherapyProgram therapyProgram = queree.uniqueResult();

            if(therapyProgram != null){
                String id = therapyProgram.getProgram_Id(); //MT001
                String subString = id.substring(2); //001
                int i =  Integer.parseInt(subString); //1
                int newIndex = i + 1;
                return String.format("MT%02d",newIndex);
            }else {
                return "MT001";
            }
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean delete(String id){
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM TherapyProgram WHERE program_Id = :id");
            query.setParameter("id", id);
            int result = query.executeUpdate();
        }finally {
            session.getTransaction().commit();
            session.close();
        }
        return true;

    }

    @Override
    public boolean save(TherapyProgram dto){
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            session.persist(dto);
        }finally {
            session.getTransaction().commit();
            session.close();
        }
        return true;
    }

    @Override
    public boolean update(TherapyProgram dto){
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();
            session.merge(dto.getProgram_Id(), dto);
        }finally {
            session.getTransaction().commit();
            session.close();
        }
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
