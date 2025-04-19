package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.RegistrationBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.RegistrationDAO;
import com.example.thementaltheraphyhelthfinal.dto.PatientDto;
import com.example.thementaltheraphyhelthfinal.dto.RegistrationDto;
import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.Registration;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;

import java.util.ArrayList;

public class RegistrationBOImpl implements RegistrationBO {
    //======
    private RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.REGISTRATION);
    //======
    @Override
    public String genaratenewId() {
        return registrationDAO.generateNewId();
    }

    @Override
    public boolean save(RegistrationDto registrationDto) {
        //CREATED THE PATIENT OBJ
        PatientDto patientDto = registrationDto.getPatient();
        Patient patient = new Patient(patientDto.getPatient_Id(),patientDto.getName(), patientDto.getEmail(), patientDto.getAddress(), patientDto.getContact());

        //CREATED THE THERAPY PROGRAM
        TherapyProgramDto therapyProgramDto = registrationDto.getTherapyProgram();
        TherapyProgram therapyProgram = new TherapyProgram(therapyProgramDto.getProgram_Id(), therapyProgramDto.getName(), therapyProgramDto.getDuration(), therapyProgramDto.getFee());

        return registrationDAO.save(new Registration(registrationDto.getRegistration_Id(), patient, therapyProgram));
    }

    @Override
    public ArrayList<RegistrationDto> getAll() {
        ArrayList<RegistrationDto> dtos = new ArrayList<>();
        ArrayList<Registration> all = registrationDAO.getAll();
        for(Registration registration : all){
            RegistrationDto registrationDto = new RegistrationDto(
                registration.getRegistration_Id(),
                //HERE CREATE THE PATIENT OBJ
                new PatientDto(registration.getPatient().getPatient_Id(), registration.getPatient().getName(),registration.getPatient().getEmail(), registration.getPatient().getAddress(), registration.getPatient().getContact()),
                //HERE CREATE THE THERAPY PROGRAM OBJ
                new TherapyProgramDto(registration.getTherapyProgram().getProgram_Id(), registration.getTherapyProgram().getName(), registration.getTherapyProgram().getDuration(), registration.getTherapyProgram().getFee())
            );

            dtos.add(registrationDto);
        }
        return dtos;
    }
}
