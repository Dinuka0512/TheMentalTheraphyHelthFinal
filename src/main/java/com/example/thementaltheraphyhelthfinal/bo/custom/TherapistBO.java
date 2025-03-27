package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.TherapistDto;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;

import java.util.ArrayList;

public interface TherapistBO extends SuperBo {
    ArrayList<TherapistDto> loadTable();
    String genarateID();
    boolean delete(TherapistDto therapist);
    boolean save(TherapistDto dto);
    boolean isValidToSave(String email);
    boolean isValidToUpdate(String email, String id);
    boolean update(TherapistDto dto);
}
