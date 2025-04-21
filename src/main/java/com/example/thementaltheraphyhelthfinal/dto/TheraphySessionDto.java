package com.example.thementaltheraphyhelthfinal.dto;

import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TheraphySessionDto {
    private String session_Id;
    private LocalDate date;
    private double amount;

    private TherapyProgramDto program;

    private PatientDto patient;

    private String therapist_Id;
}
