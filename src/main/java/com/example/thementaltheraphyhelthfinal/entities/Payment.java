package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    private String Payment_Id;

    private double amount;
    private LocalDate date;

    private String patient_id;
    private String session_Id;
}
