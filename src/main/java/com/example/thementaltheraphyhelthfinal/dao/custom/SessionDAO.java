package com.example.thementaltheraphyhelthfinal.dao.custom;

import com.example.thementaltheraphyhelthfinal.dao.CrudDAO;
import com.example.thementaltheraphyhelthfinal.dao.SuperDAO;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.entities.TheraphySession;

import java.util.ArrayList;

public interface SessionDAO extends SuperDAO, CrudDAO<TheraphySession> {
    TheraphySession getProgram(String id);
    ArrayList<String> sessionBookdedDates(String therapist);
}
