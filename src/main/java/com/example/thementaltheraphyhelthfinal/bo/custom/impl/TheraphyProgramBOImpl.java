package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.TheraphyProgramBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.TheraphyProgramDAO;
import com.example.thementaltheraphyhelthfinal.dto.tm.TheraphyProgramTm;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;

import java.util.ArrayList;

public class TheraphyProgramBOImpl implements TheraphyProgramBO {

    private TheraphyProgramDAO therapyProgramDAO = (TheraphyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.PROGRAM);

    @Override
    public ArrayList<TheraphyProgramTm> loadTable() {
        ArrayList<TherapyProgram> therapyPrograms = therapyProgramDAO.getAll();
        ArrayList<TheraphyProgramTm> theraphyProgramTms = new ArrayList<>();

        if(!therapyPrograms.isEmpty()){
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

    @Override
    public ArrayList<TheraphyProgramTm> searchFromTable(String name) {
        ArrayList<TheraphyProgramTm> arrayList = new ArrayList<>();
        ArrayList<TherapyProgram> programs = therapyProgramDAO.searchFromTable(name);

        if(!programs.isEmpty()){
            for (TherapyProgram therapyProgram : programs){
                TheraphyProgramTm theraphyProgramTm = new TheraphyProgramTm(
                        therapyProgram.getProgram_Id(),
                        therapyProgram.getName(),
                        therapyProgram.getDuration(),
                        therapyProgram.getFee()
                );

                arrayList.add(theraphyProgramTm);
            }
        }

        return arrayList;
    }


}
