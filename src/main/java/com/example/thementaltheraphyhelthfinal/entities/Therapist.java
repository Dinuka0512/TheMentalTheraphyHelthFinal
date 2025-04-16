package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Therapist {
    @Id
    private String therapist_Id;
    private String name;

    @Column(unique = true)
    private String email;

    private String address;
    private String contact;

    @ManyToOne
    private TherapyProgram program;


    public Therapist(String therapist_Id, String name, String email, String address, String contact) {
        this.therapist_Id = therapist_Id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
    }

}
