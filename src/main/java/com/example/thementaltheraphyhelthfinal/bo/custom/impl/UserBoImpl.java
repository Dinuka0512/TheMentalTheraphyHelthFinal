package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.UserBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.UserDAO;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.entities.User;
import com.example.thementaltheraphyhelthfinal.util.AlertsPack.CustomAlerts;
import com.example.thementaltheraphyhelthfinal.util.exceptionsPack.CustomEXception;

import java.util.ArrayList;

public class UserBoImpl implements UserBO {
    //===
    private UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.USER);
    //===

    @Override
    public UserDto getUserDetails(String email) {
            User user = userDAO.getUserDetails(email);
            return (user != null)? new UserDto(user.getUser_Id(), user.getName(), user.getAddress(), user.getJobRole(), user.getEmail(), user.getContact(), user.getPassword()) : null;
    }

    @Override
    public ArrayList<UserDto> loadTable() {
        ArrayList<User> userList = userDAO.getAll();
        ArrayList<UserDto> dtoLists =new ArrayList<>();
        if(!userList.isEmpty()){
            for(User user : userList){
                UserDto userDto = new UserDto(
                        user.getUser_Id(),
                        user.getName(),
                        user.getAddress(),
                        user.getJobRole(),
                        user.getPassword(),
                        user.getContact(),
                        user.getEmail()
                );
                dtoLists.add(userDto);
            }
        }

        return dtoLists;
    }

    @Override
    public boolean saveUser(UserDto user) {
        if(userDAO.save(new User(user.getUser_Id(), user.getName(), user.getAddress(), user.getJobRole(), user.getEmail(), user.getContact(), user.getPassword()))){
            CustomAlerts.saved();
            return true;
        }
        return false;
    }

    @Override
    public boolean isUniqueEmail(String email) {
        return userDAO.isUniqueEmail(email);
    }
}
