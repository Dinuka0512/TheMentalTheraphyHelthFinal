package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.UserBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.UserDAO;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.entities.User;
import com.example.thementaltheraphyhelthfinal.util.exceptionsPack.CustomEXception;

public class UserBoImpl implements UserBO {
    //===
    private UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.USER);
    //===

    @Override
    public UserDto getUserDetails(String email) {
        try {
            User user = userDAO.getUserDetails(email);
            CustomEXception.IsNull(user); //HERE CHECK THE EXCEPTION
            return new UserDto(user.getUser_Id(), user.getName(), user.getAddress(), user.getJobRole(), user.getEmail(), user.getContact());
        } catch (CustomEXception e) {
            e.printStackTrace();
        }
        return null;
    }
}
