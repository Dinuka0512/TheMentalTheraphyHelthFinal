package com.example.thementaltheraphyhelthfinal.dao;

import com.example.thementaltheraphyhelthfinal.dao.impl.UserDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum getDAOType{
        USER
    }

    public SuperDAO getDAO(getDAOType type){
        switch (type){
            case USER -> {
                return new UserDAOImpl();
            }

            default -> {
                return null;
            }
        }
    }
}
