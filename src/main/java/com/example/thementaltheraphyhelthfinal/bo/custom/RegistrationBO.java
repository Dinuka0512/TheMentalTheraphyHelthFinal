package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.RegistrationDto;
import com.example.thementaltheraphyhelthfinal.entities.Registration;

import java.util.ArrayList;

public interface RegistrationBO extends SuperBo {
    String genaratenewId();

    boolean save(RegistrationDto registrationDto);
    ArrayList<RegistrationDto> getAll();

    boolean update(RegistrationDto registrationDto);
}
