package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.SessionBO;
import com.example.thementaltheraphyhelthfinal.config.FactoryConfig;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.PatienDAO;
import com.example.thementaltheraphyhelthfinal.dao.custom.SessionDAO;
import com.example.thementaltheraphyhelthfinal.dao.custom.TheraphyProgramDAO;
import com.example.thementaltheraphyhelthfinal.dto.PatientDto;
import com.example.thementaltheraphyhelthfinal.dto.TheraphySessionDto;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.TheraphySession;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionBOImpl implements SessionBO {
    //=======
    private PatienDAO patienDAO = (PatienDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.PATIENTS);
    private TheraphyProgramDAO theraphyProgramDAO = (TheraphyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.PROGRAM);
    private SessionDAO sessionDAO = (SessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.SESSION);
    //=======
    @Override
    public ArrayList<TheraphySessionDto> getAllSessions() {
        //HERE GET THE ALL SESSION DETAILS
        ArrayList<TheraphySession> theraphySessions = sessionDAO.getAll();
        ArrayList<TheraphySessionDto> theraphySessionDtos = new ArrayList<>();


        for(TheraphySession theraphySession : theraphySessions){
            //HERE GET THE PROGRAM DETAILS
            TherapyProgram program = theraphyProgramDAO.getDetails(theraphySession.getProgram().getProgram_Id());

            //HERE GET THE PATIENT DETAILS LIST
            Patient patient = patienDAO.getDetails(theraphySession.getPatient().getPatient_Id());

            //HERE SET THE RETURN TYPE
            TheraphySessionDto theraphySessionDto = new TheraphySessionDto(
                   theraphySession.getSession_Id(),
                   theraphySession.getDate(),
                   theraphySession.getAmount(),
                   //Program
                    new TherapyProgramDto(
                            program.getProgram_Id(),
                            program.getName(),
                            program.getDuration(),
                            program.getFee()
                    ),

                    //PATIENT
                    new PatientDto(
                            patient.getPatient_Id(),
                            patient.getName(),
                            patient.getEmail(),
                            patient.getAddress(),
                            patient.getContact()
                    ),
                    theraphySession.getTherapist_Id()
           );

            theraphySessionDtos.add(theraphySessionDto);
        }
        return theraphySessionDtos;
    }

    @Override
    public String genarateNewId() {
        return sessionDAO.generateNewId();
    }

    @Override
    public boolean delete(String id) {
        return sessionDAO.delete(id);
    }

    @Override
    public boolean save(TheraphySessionDto theraphySessionDto) {
        TheraphySession theraphySession = new TheraphySession(
                theraphySessionDto.getSession_Id(),
                theraphySessionDto.getDate(),
                theraphySessionDto.getAmount(),
                new TherapyProgram(
                        theraphySessionDto.getProgram().getProgram_Id(),
                        theraphySessionDto.getProgram().getName(),
                        theraphySessionDto.getProgram().getDuration(),
                        theraphySessionDto.getProgram().getFee()
                ),
                new Patient(
                        theraphySessionDto.getPatient().getPatient_Id(),
                        theraphySessionDto.getPatient().getName(),
                        theraphySessionDto.getPatient().getEmail(),
                        theraphySessionDto.getPatient().getAddress(),
                        theraphySessionDto.getPatient().getContact()
                ),
                theraphySessionDto.getTherapist_Id()
        );

        return sessionDAO.save(theraphySession);
    }
}
