package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.SessionDAO;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.entities.TheraphySession;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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
    public boolean save(TheraphySession dto) {
        return false;
    }

    @Override
    public boolean update(TheraphySession dto) {
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
        Session session = FactoryConfig.getInstance().getSession();
        session.beginTransaction();
        NativeQuery<TheraphySession> query = session.createNativeQuery("SELECT * FROM TheraphySession ORDER BY session_Id DESC LIMIT 1", TheraphySession.class);
        TheraphySession result = query.getSingleResult();
        if(result!=null){
            String last_Id = result.getSession_Id(); //S001
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
