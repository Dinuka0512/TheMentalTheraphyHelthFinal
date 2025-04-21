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
    private LocalDate date;
    private double amount;
    private String time;

    @ManyToOne
    @JoinColumn(name = "program_Id")
    private TherapyProgram program;

    @ManyToMany
    private List<Patient> patient;
}
