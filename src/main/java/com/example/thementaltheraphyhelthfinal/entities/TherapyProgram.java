package com.example.thementaltheraphyhelthfinal.entities;

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
public class TherapyProgram {
    @Id
    private String program_Id;
    private String name;
    private String duration;
    private double fee;
}
