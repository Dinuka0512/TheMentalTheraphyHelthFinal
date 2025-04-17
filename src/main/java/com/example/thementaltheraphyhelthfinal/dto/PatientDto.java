package com.example.thementaltheraphyhelthfinal.dto;

import com.example.thementaltheraphyhelthfinal.entities.Payment;
import com.example.thementaltheraphyhelthfinal.entities.TheraphySession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientDto {
    private String patient_Id;
    private String name;
    private String email;
    private String address;
    private String contact;

    private List<String> theraphySession;

    private PaymentDto payment;

    public PatientDto(String patient_Id, String name, String email, String address, String contact) {
        this.patient_Id = patient_Id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }
}
