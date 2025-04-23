package com.example.thementaltheraphyhelthfinal.dao.custom.impl;

import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.custom.PatienDAO;
import com.example.thementaltheraphyhelthfinal.dto.PatientDto;
import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.User;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatienDAO {

    @Override
    public String generateNewId() {
        Session session = FactoryConfig.getInstance().getSession();
        try{
            session.beginTransaction();

            NativeQuery<Patient> nativeQuery = session.createNativeQuery("SELECT * FROM Patient GROUP BY patient_Id desc LIMIT 1", Patient.class);
            Patient patient = nativeQuery.uniqueResult();

            if(patient!=null){
                String lastId = patient.getPatient_Id(); //P001
                String subId = lastId.substring(2); //001
                int i = Integer.parseInt(subId); //1
                i = i + 1; //2
                return String.format("P%03d",i); //P002
            }
        }finally {
            session.getTransaction().commit();
            session.close();
        }
        return "P001";
    }

    @Override
    public ArrayList<Patient> getAll() {
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();

            Query<Patient> query = session.createQuery("FROM Patient", Patient.class);
            List<Patient> results = query.getResultList();
            return (ArrayList<Patient>) results;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean isValidToSave(String email) {
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();

            NativeQuery<Patient> query = session.createNativeQuery("SELECT * FROM Patient WHERE email = :email", Patient.class);
            query.setParameter("email",email);
            List<Patient> resultList = query.getResultList();
            return resultList.isEmpty();
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean isValidToUpdate(String email, String id) {
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            Query<Patient> query = session.createQuery("FROM Patient WHERE email = :email AND patient_Id != :id", Patient.class);
            query.setParameter("email",email);
            query.setParameter("id",id);

            List<Patient> resultList = query.getResultList();
            return resultList.isEmpty();
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean delete(Patient dto) {
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            session.remove(dto);
        }finally {
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }

    @Override
    public Patient getDetails(String selectedItem) {
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            Patient patient = session.get(Patient.class, selectedItem);
            return patient;
        }finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public boolean save(Patient dto) {
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            session.persist(dto);
        }finally {
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }

    @Override
    public boolean update(Patient dto) {
        Session session = FactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            session.merge(dto);
        }finally {
            session.getTransaction().commit();
            session.close();
            return true;
        }
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
    public Patient search(String id) {
        return null;
    }
}
