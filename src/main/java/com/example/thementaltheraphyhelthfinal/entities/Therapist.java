package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
}
