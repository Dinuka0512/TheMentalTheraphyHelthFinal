package com.example.thementaltheraphyhelthfinal.dto;

import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class RegistrationDto {
    private String Registration_Id;
    private PatientDto patient;
    private TherapyProgramDto therapyProgram;
}
