package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.tm.TheraphyProgramTm;

import java.util.ArrayList;

public interface TheraphyProgramBO extends SuperBo {
    public ArrayList<TheraphyProgramTm> loadTable();
}
