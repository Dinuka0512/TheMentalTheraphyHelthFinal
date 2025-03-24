package com.example.thementaltheraphyhelthfinal.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDto {
    @Id
    private String patient_Id;
    private String name;

    @Column(unique = true)
    private String email;

    private String address;
    private String contact;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private UserDto user;
//
//    @OneToOne(mappedBy = "patient")
//    private Payment payment;
//
//    @ManyToMany(mappedBy = "patients")
//    private List<TherapyProgram> therapyPrograms;
//
//    @OneToMany(mappedBy = "patient")
//    private List<org.example.entities.Associatentities.sessoins> sessoins;
}
