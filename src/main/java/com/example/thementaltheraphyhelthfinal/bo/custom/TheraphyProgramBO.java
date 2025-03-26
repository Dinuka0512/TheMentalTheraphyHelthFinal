package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.tm.TheraphyProgramTm;

import java.util.ArrayList;

public interface TheraphyProgramBO extends SuperBo {
    ArrayList<TheraphyProgramTm> loadTable();
    ArrayList<TheraphyProgramTm> searchFromTable(String name);
    String generateNewId();
    boolean delete(String id);
}
