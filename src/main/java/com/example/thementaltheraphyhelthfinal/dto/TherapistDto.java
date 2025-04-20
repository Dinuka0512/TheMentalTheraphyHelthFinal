package com.example.thementaltheraphyhelthfinal.dto;

import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapistDto {
    private String therapist_Id;
    private String name;
    private String email;
    private String address;
    private String contact;
    private TherapyProgramDto programDto;
}
