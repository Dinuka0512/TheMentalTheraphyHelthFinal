package com.example.thementaltheraphyhelthfinal.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TheraphyProgramTm {
    private String program_Id;
    private String name;
    private String duration;
    private double fee;
}
