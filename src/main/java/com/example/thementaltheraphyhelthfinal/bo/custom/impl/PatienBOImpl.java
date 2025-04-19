package com.example.thementaltheraphyhelthfinal.bo.custom.impl;

import com.example.thementaltheraphyhelthfinal.bo.custom.PatientBO;
import com.example.thementaltheraphyhelthfinal.dao.DAOFactory;
import com.example.thementaltheraphyhelthfinal.dao.custom.PatienDAO;
import com.example.thementaltheraphyhelthfinal.dto.PatientDto;
import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;

import java.util.ArrayList;

public class PatienBOImpl implements PatientBO {
    private PatienDAO patienDAO = (PatienDAO) DAOFactory.getInstance().getDAO(DAOFactory.getDAOType.PATIENTS);
    @Override
    public ArrayList<PatientDto> getAll() {
        ArrayList<Patient> result = patienDAO.getAll();
        ArrayList<PatientDto> dtos = new ArrayList<>();
        for(Patient patient : result){
            PatientDto dto = new PatientDto(patient.getPatient_Id(), patient.getName(), patient.getEmail(), patient.getAddress(), patient.getContact());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public String generateNewId() {
        return patienDAO.generateNewId();
    }

    @Override
    public boolean isValidToSave(String email) {
        return patienDAO.isValidToSave(email);
    }

    @Override
    public boolean save(PatientDto patientDto) {
        Patient patient = new Patient(patientDto.getPatient_Id(), patientDto.getName(), patientDto.getEmail(), patientDto.getAddress(), patientDto.getContact());
        return patienDAO.save(patient);
    }

    @Override
    public boolean isValidToUpdate(String email, String id) {
        return patienDAO.isValidToUpdate(email,id);
    }

    @Override
    public boolean update(PatientDto dto) {
        Patient patient = new Patient(dto.getPatient_Id(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getContact());
        return patienDAO.update(patient);
    }

    @Override
    public boolean delete(PatientDto dto) {
        Patient patient = new Patient(dto.getPatient_Id(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getContact());
        return patienDAO.delete(patient);
    }

    @Override
    public ArrayList<String> getAllIds() {
        ArrayList<Patient> all = patienDAO.getAll();
        ArrayList<String> ids = new ArrayList<>();
        for(Patient dto : all){
            ids.add(dto.getPatient_Id());
        }

        return ids;
    }

    @Override
    public PatientDto getDetails(String selectedItem) {
        Patient patient = patienDAO.getDetails(selectedItem);
        return (patient!=null)? new PatientDto(patient.getPatient_Id(), patient.getName(), patient.getEmail(), patient.getAddress(), patient.getContact()): null;
    }

}
