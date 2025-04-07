package com.example.thementaltheraphyhelthfinal.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientTm {
    private String patient_Id;
    private String name;
    private String email;
    private String address;
    private String contact;
}
