package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.TheraphyProgramTm;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;

import java.util.ArrayList;

public interface TheraphyProgramBO extends SuperBo {
    ArrayList<TheraphyProgramTm> loadTable();
    ArrayList<TheraphyProgramTm> searchFromTable(String name);
    String generateNewId();
    boolean delete(String id);
    boolean update(TherapyProgramDto dto);
    boolean save(TherapyProgramDto dto);
}
