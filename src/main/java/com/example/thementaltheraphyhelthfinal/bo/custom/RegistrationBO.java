package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.RegistrationDto;

public interface RegistrationBO extends SuperBo {
    String genaratenewId();

    boolean save(RegistrationDto registrationDto);
}
