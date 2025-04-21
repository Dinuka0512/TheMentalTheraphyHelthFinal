package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.TherapistBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.TherapistDAO;
import com.example.thementaltheraphyhelthfinal.dto.TherapistDto;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;

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
                TherapistDto dto = new TherapistDto(therapist.getTherapist_Id(), therapist.getName(), therapist.getEmail(), therapist.getAddress(), therapist.getContact(),new TherapyProgramDto(therapist.getProgram().getProgram_Id(), therapist.getProgram().getName(), therapist.getProgram().getDuration(), therapist.getProgram().getFee()));
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
    public boolean delete(String id) {
        return therapistDAO.delete(id);
    }

    @Override
    public boolean save(TherapistDto dto) {
        TherapyProgram therapyProgram = new TherapyProgram(
                dto.getProgramDto().getProgram_Id(),
                dto.getProgramDto().getName(),
                dto.getProgramDto().getDuration(),
                dto.getProgramDto().getFee()
        );

        return therapistDAO.save(
                new Therapist(
                        dto.getTherapist_Id(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getAddress(),
                        dto.getContact(),
                        therapyProgram
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
        TherapyProgram therapyProgram = new TherapyProgram(
                dto.getProgramDto().getProgram_Id(),
                dto.getProgramDto().getName(),
                dto.getProgramDto().getDuration(),
                dto.getProgramDto().getFee()
        );

        return therapistDAO.update(
                new Therapist(
                        dto.getTherapist_Id(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getAddress(),
                        dto.getContact(),
                        therapyProgram
                )
        );
    }

    @Override
    public ArrayList<String> loadtherapist(String programId) {
        return therapistDAO.loadtherapist(programId);
    }

    @Override
    public TherapistDto getTherapistDetails(String selectedItem) {
        Therapist therapist = therapistDAO.getTherapistDetails(selectedItem);
        return (therapist!=null)?
                new TherapistDto(
                        therapist.getTherapist_Id(),
                        therapist.getName(),
                        therapist.getEmail(),
                        therapist.getAddress(),
                        therapist.getContact(),
                        new TherapyProgramDto(
                                therapist.getProgram().getProgram_Id(),
                                therapist.getProgram().getName(),
                                therapist.getProgram().getDuration(),
                                therapist.getProgram().getFee()
                        )
                ) : null;
    }
}
