package com.example.thementaltheraphyhelthfinal.bo.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.UserBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.UserDAO;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.entities.User;

public class UserBoImpl implements UserBO {
    //===
    private UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.USER);
    //===

    @Override
    public UserDto getUserDetails(String email) {
        User user = userDAO.getUserDetails(email);
        return (user != null)? new UserDto(
                user.getUser_Id(),
                user.getName(),
                user.getEmail(),
                user.getContact(),
                user.getAddress(),
                user.getJobRole()
        ): null;
    }
}
