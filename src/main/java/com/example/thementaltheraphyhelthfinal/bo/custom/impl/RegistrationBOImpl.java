package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.RegistrationBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.RegistrationDAO;

public class RegistrationBOImpl implements RegistrationBO {
    //======
    private RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.REGISTRATION);
    //======
    @Override
    public String genaratenewId() {
        return registrationDAO.generateNewId();
    }
}
