package com.example.thementaltheraphyhelthfinal.dto;

import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessoinsDto {
    @EmbeddedId
    private com.example.thementaltheraphyhelthfinal.entities.Associatentities.sessionIds sessionIds;

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
