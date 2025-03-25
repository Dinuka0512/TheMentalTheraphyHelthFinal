package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.TheraphyProgramBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.TheraphyProgramDAO;
import com.example.thementaltheraphyhelthfinal.dto.tm.TheraphyProgramTm;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;

import java.util.ArrayList;

public class TherapgyTheraphyProgramBOImpl implements TheraphyProgramBO {

    private TheraphyProgramDAO therapyProgramDAO = (TheraphyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.PROGRAM);

    @Override
    public ArrayList<TheraphyProgramTm> loadTable() {
        ArrayList<TherapyProgram> therapyPrograms = therapyProgramDAO.getAll();
        ArrayList<TheraphyProgramTm> theraphyProgramTms = new ArrayList<>();

        if(therapyPrograms != null){
            for (TherapyProgram therapyProgram : therapyPrograms){
                TheraphyProgramTm theraphyProgramTm = new TheraphyProgramTm(
                        therapyProgram.getProgram_Id(),
                        therapyProgram.getName(),
                        therapyProgram.getDuration(),
                        therapyProgram.getFee()
                );

                theraphyProgramTms.add(theraphyProgramTm);
            }
        }
        return theraphyProgramTms;
    }
}
