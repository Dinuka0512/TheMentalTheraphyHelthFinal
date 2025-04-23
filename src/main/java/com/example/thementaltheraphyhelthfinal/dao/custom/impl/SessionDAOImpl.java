package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.SessionDAO;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.DashTherapistTm;
import com.example.thementaltheraphyhelthfinal.entities.TheraphySession;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionDAOImpl implements SessionDAO {
    @Override
    public ArrayList<TheraphySession> getAll() {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        Query<TheraphySession> query = session.createQuery("FROM TheraphySession", TheraphySession.class);
        List<TheraphySession> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (ArrayList<TheraphySession>) resultList;
    }

    @Override
    public TheraphySession getProgram(String id) {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        Query<TheraphySession> query = session.createQuery("FROM TheraphySession WHERE session_Id = :id", TheraphySession.class);
        query.setParameter("id", id);
        TheraphySession theraphySession = query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return theraphySession;
    }

    @Override
    public ArrayList<String> sessionBookdedDates(String therapist) {
        Session session = FactoryConfig.factoryCongig.getSession();
        session.beginTransaction();
        NativeQuery<TheraphySession> query = session.createNativeQuery("SELECT * FROM TheraphySession WHERE therapist_Id = :id", TheraphySession.class);
        query.setParameter("id", therapist);
        List<TheraphySession> list = query.getResultList();
        if(list!=null){
            ArrayList<String> ids = new ArrayList<>();
            for(TheraphySession theraphySession : list){
                ids.add(theraphySession.getDate().toString());
            }

            return ids;
        }

        return null;
    }

    @Override
    public String getTodaySessionsBooked() {
        Session session = FactoryConfig.factoryCongig.getSession();
        session.beginTransaction();
        String date = String.valueOf(LocalDate.now());
        NativeQuery<TheraphySession> query = session.createNativeQuery("SELECT * FROM TheraphySession WHERE date = :date", TheraphySession.class);
        query.setParameter("date", date);
        List<TheraphySession> list = query.getResultList();
        return list.isEmpty()? "0" : (list.size() < 10)? "0" + list.size() : Integer.toString(list.size());
    }

    @Override
    public ArrayList<DashTherapistTm> selectTherapistAndSessionCount() {
        Session session = FactoryConfig.factoryCongig.getSession();
        session.beginTransaction();

        // Native SQL query
        NativeQuery<Object[]> query = session.createNativeQuery(
                "SELECT therapist_Id, COUNT(session_Id) AS session_count " +
                        "FROM TheraphySession GROUP BY therapist_Id ORDER BY session_count DESC LIMIT 3"
        );

        List<Object[]> results = query.getResultList();
        ArrayList<DashTherapistTm> therapistTms = new ArrayList<>();

        for (Object[] row : results) {
            String therapistId = (String) row[0];
            Long sessionCount = ((Number) row[1]).longValue();

            Therapist therapist = session.get(Therapist.class, therapistId);

            DashTherapistTm tm = new DashTherapistTm(
                    therapist.getName(),
                    sessionCount.intValue()
            );

            therapistTms.add(tm);
        }

        session.getTransaction().commit();
        session.close();
        return therapistTms;
    }


    @Override
    public boolean save(TheraphySession dto) {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        session.persist(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TheraphySession dto) {
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
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        TheraphySession theraphySession = session.get(TheraphySession.class, id);

        if(theraphySession != null){
            session.remove(theraphySession);
        }

        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewId() {
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        NativeQuery<TheraphySession> query = session.createNativeQuery("SELECT * FROM TheraphySession ORDER BY session_Id DESC LIMIT 1", TheraphySession.class);
        List<TheraphySession> list = query.getResultList();
        if(!list.isEmpty()){
            String last_Id = list.getFirst().getSession_Id(); //S001
            String sub = last_Id.substring(1); //001
            int i = Integer.parseInt(sub); //1
            i = i + 1;
            return String.format("S%03d", i);
        }
        return "S001";
    }

    @Override
    public TheraphySession search(String id) {
        return null;
    }
}
