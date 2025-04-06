package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TheraphySession {
    @Id
    private String session_Id;
    private String description;
    private LocalDate date;
    private double amount;
    private String time;

    @ManyToMany
    private List<Patient> patient;

    @ManyToOne
    @JoinColumn(name = "therapist_Id")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "program_Id")
    private TherapyProgram program;
}
