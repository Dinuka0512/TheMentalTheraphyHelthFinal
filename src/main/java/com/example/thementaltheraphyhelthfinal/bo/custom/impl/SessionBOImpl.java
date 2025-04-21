package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.SessionBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.SessionDAO;
import com.example.thementaltheraphyhelthfinal.dto.TheraphySessionDto;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.entities.TheraphySession;

import java.util.ArrayList;

public class SessionBOImpl implements SessionBO {
    //=======
    private SessionDAO sessionDAO = (SessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.SESSION);
    //=======
    @Override
    public ArrayList<TheraphySessionDto> getAllSessions() {
        ArrayList<TheraphySession> theraphySessions = sessionDAO.getAll();
        ArrayList<TheraphySessionDto> theraphySessionDtos = new ArrayList<>();

        for(TheraphySession theraphySession : theraphySessions){
            TheraphySessionDto dto = new TheraphySessionDto(
                    theraphySession.getSession_Id(),
                    theraphySession.getDate(),
                    theraphySession.getAmount(),
                    theraphySession.getProgram()
            );

            theraphySessionDtos.add(dto);
        }

        return theraphySessionDtos;
    }

    @Override
    public TheraphySessionDto getProgram(String id) {
        TheraphySession program = sessionDAO.getProgram(id);
        return (program != null)? new TheraphySessionDto(program.getSession_Id(), program.getDate(), program.getAmount(), program.getProgram()): null;
    }

    @Override
    public String genarateNewId() {
        return sessionDAO.generateNewId();
    }
}
