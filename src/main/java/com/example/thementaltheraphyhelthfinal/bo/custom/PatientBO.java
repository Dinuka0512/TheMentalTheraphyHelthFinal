package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.PatientDto;
import com.example.thementaltheraphyhelthfinal.entities.Patient;

import java.util.ArrayList;

public interface PatientBO extends SuperBo {
    ArrayList<PatientDto> getAll();
    String generateNewId();

    boolean isValidToSave(String email);

    boolean save(PatientDto patientDto);
}
