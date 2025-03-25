package com.example.thementaltheraphyhelthfinal.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class TheraphySessionDto {
    @Id
    private String session_Id;
    private String description;
    private LocalDate date;
    private double amount;
    private String time;

    @ManyToOne
    @JoinColumn(name = "patient_Id")
    private PatientDto patient;

    @ManyToOne
    @JoinColumn(name = "therapist_Id")
    private TherapistDto therapist;

    @ManyToOne
    @JoinColumn(name = "program_Id")
    private TherapyProgramDto program;
}
