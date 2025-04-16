package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    private String patient_Id;
    private String name;

    @Column(unique = true)
    private String email;

    private String address;
    private String contact;


    @ManyToMany
    private TheraphySession theraphySession;

    @OneToOne
    private Payment payment;

    public Patient(String patient_Id, String name, String email, String address, String contact) {
        this.patient_Id = patient_Id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }
}
