package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.TherapistBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.TherapistDAO;
import com.example.thementaltheraphyhelthfinal.dto.TherapistDto;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;

import java.util.ArrayList;

public class TherapistBOimpl implements TherapistBO {
    //==================
    private TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.THERAPIST);
    //==================

    @Override
    public ArrayList<TherapistDto> loadTable() {
        ArrayList<Therapist> therapists = therapistDAO.getAll();
        if(therapists!=null){
            ArrayList<TherapistDto> therapistDtos = new ArrayList<>();
            for(Therapist therapist : therapists){
                TherapistDto dto = new TherapistDto(therapist.getTherapist_Id(), therapist.getName(), therapist.getEmail(), therapist.getAddress(), therapist.getContact());
                therapistDtos.add(dto);
            }
            return therapistDtos;
        }
        return null;
    }

    @Override
    public String genarateID() {
        return therapistDAO.generateNewId();
    }
}
