package com.example.thementaltheraphyhelthfinal.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {
    @Id
    private String Payment_Id;
    private double amount;
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "patient_Id")
    private PatientDto patient;
}
