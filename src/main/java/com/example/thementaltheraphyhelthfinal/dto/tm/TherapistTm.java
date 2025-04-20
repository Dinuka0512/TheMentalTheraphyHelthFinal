package com.example.thementaltheraphyhelthfinal.dto.tm;

import com.example.thementaltheraphyhelthfinal.dto.TherapyProgramDto;
import com.example.thementaltheraphyhelthfinal.entities.TherapyProgram;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TherapistTm {
    private String therapist_Id;
    private String name;
    private String email;
    private String address;
    private String contact;
    private TherapyProgramDto therapyProgramDto;
}
