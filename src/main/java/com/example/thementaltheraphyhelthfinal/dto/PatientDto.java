package com.example.thementaltheraphyhelthfinal.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
}
