package com.example.thementaltheraphyhelthfinal.dao.custom;

import com.example.thementaltheraphyhelthfinal.dao.CrudDAO;
import com.example.thementaltheraphyhelthfinal.dao.SuperDAO;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;

import java.util.ArrayList;

public interface TheraphyProgramDAO extends SuperDAO, CrudDAO<TherapyProgram> {
    ArrayList<TherapyProgram> searchFromTable(String name);
}
