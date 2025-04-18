package com.example.thementaltheraphyhelthfinal.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationTm {
    private String registration_Id;
    private String patient_Id;
    private String PatientName;
    private String program_Id;
    private String program_Name;
    private double fee;
}
