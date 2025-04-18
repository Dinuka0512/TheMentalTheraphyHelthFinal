package com.example.thementaltheraphyhelthfinal.dao;

import com.example.thementaltheraphyhelthfinal.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum getDAOType{
        USER, PROGRAM,THERAPIST, PATIENTS, SESSION, PAYMENT, REGISTRATION
    }

    public SuperDAO getDAO(getDAOType type){
        switch (type){
            case USER -> {
                return new UserDAOImpl();
            }
            case PROGRAM -> {
                return new TheraphyProgramDAOImpl();
            }
            case THERAPIST -> {
                return new TherapistDAOImpl();
            }
            case PATIENTS -> {
                return new PatientDAOImpl();
            }
            case SESSION -> {
                return new SessionDAOImpl();
            }
            case PAYMENT -> {
                return new PaymentDAOImpl();
            }
            case REGISTRATION -> {
                return new RegistrationDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
