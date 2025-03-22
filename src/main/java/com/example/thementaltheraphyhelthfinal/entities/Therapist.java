package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.Column;
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
public class Therapist {
    @Id
    private String therapist_Id;
    private String name;

    @Column(unique = true)
    private String email;

    private String address;
    private String contact;

    @ManyToMany
    private List<TherapyProgram> therapyPrograms;

//    @OneToMany(mappedBy = "therapist")
//    private List<sessoins> sessions;
}
