package com.example.thementaltheraphyhelthfinal.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapyProgramDto {
    @Id
    private String program_Id;
    private String name;
    private String duration;
    private double fee;

    @ManyToMany
    private List<PatientDto> patients;

//    @ManyToMany(mappedBy = "therapyPrograms")
//    private List<Therapist> therapists;
}
