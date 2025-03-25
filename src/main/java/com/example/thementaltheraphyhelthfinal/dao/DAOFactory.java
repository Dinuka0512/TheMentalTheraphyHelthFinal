package com.example.thementaltheraphyhelthfinal.dao;

import com.example.thementaltheraphyhelthfinal.dao.custom.impl.TheraphyTheraphyProgramDAOImpl;
import com.example.thementaltheraphyhelthfinal.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum getDAOType{
        USER, PROGRAM
    }

    public SuperDAO getDAO(getDAOType type){
        switch (type){
            case USER -> {
                return new UserDAOImpl();
            }
            case PROGRAM -> {
                return new TheraphyTheraphyProgramDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
