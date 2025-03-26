package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.UserTm;
import com.example.thementaltheraphyhelthfinal.entities.User;

import java.util.ArrayList;

public interface UserBO extends SuperBo {
    UserDto getUserDetails(String email);
    ArrayList<UserDto> loadTable();
    boolean saveUser(UserDto user);
    boolean isUniqueEmail(String email);
    boolean update(UserDto dto);
    boolean delete(int id);
}
