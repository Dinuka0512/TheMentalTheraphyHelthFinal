package com.example.thementaltheraphyhelthfinal.bo.custom;

import com.example.thementaltheraphyhelthfinal.bo.SuperBo;
import com.example.thementaltheraphyhelthfinal.dto.TheraphySessionDto;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.dto.tm.DashTherapistTm;

import java.util.ArrayList;

public interface SessionBO extends SuperBo {
    ArrayList<TheraphySessionDto> getAllSessions();
    String genarateNewId();
    boolean delete(String id);
    boolean save(TheraphySessionDto theraphySessionDto);
    ArrayList<String> sessionBookdedDates(String therapist);

    boolean update(TheraphySessionDto theraphySessionDto);

    String getTodaySessionsBooked();

    ArrayList<DashTherapistTm> selectTherapistAndSessionCount();
}

