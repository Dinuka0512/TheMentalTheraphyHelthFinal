package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.UserDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.UserTm;

import java.util.ArrayList;

public interface UserBO extends SuperBo {
    UserDto getUserDetails(String email);
    ArrayList<UserDto> loadTable();
}
