package com.example.thementaltheraphyhelthfinal.dto;

import com.example.thementaltheraphyhelthfinal.entities.Patient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDto {
    private String Payment_Id;
    private double amount;
    private LocalDate date;
    private String patient_id;
    private String session_Id;
}
