package com.example.thementaltheraphyhelthfinal.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TherapySessionTm {
    private String sessionId;
    private String patient_Id;
    private String patientName;
    private String program_Id;
    private String program_Name;
    private double program_Fee;
    private String therapist_Id;
    private String therapist_Name;
    private String date;
}

