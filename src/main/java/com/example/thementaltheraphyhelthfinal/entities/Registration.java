package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Registration {
    @Id
    private String Registration_Id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private TherapyProgram therapyProgram;
}
