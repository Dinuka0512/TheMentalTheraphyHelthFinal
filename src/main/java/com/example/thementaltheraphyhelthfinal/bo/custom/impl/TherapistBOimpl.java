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

    @Override
    public boolean delete(TherapistDto therapist) {
        return therapistDAO.delete(new Therapist(therapist.getTherapist_Id(), therapist.getName(), therapist.getEmail(), therapist.getContact(), therapist.getAddress()));
    }

    @Override
    public boolean save(TherapistDto dto) {
        return therapistDAO.save(
                new Therapist(
                        dto.getTherapist_Id(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getAddress(),
                        dto.getContact()
                )
        );
    }

    @Override
    public boolean isValidToSave(String email) {
        return therapistDAO.isValidToSave(email);
    }

    @Override
    public boolean isValidToUpdate(String email, String id) {
        return therapistDAO.isValidToUpdate(email, id);
    }

    @Override
    public boolean update(TherapistDto dto) {
        return therapistDAO.update(
                new Therapist(
                        dto.getTherapist_Id(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getAddress(),
                        dto.getContact()
                )
        );
    }
}
