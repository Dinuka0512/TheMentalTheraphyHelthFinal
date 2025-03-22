package com.example.thementaltheraphyhelthfinal.entities.Associatentities;

import com.example.thementaltheraphyhelthfinal.entities.Patient;
import com.example.thementaltheraphyhelthfinal.entities.Therapist;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class sessoins {
//    @EmbeddedId
    @Id
    private String sessionIds;

    @ManyToOne
    @JoinColumn(name = "patient_Id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapist_Id")
    private Therapist therapist;

    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Date date;
}
