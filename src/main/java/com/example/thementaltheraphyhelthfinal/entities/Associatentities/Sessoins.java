package com.example.thementaltheraphyhelthfinal.entities.Associatentities;

import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sessoins {
    @EmbeddedId
    private sessionIds sessionIds;

    @ManyToOne
    @JoinColumn(name = "patient_Id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapist_Id")
    private Therapist therapist;

    private String checkInTime;
    private String checkOutTime;

    private Date date;
}
