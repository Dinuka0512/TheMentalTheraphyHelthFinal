package com.example.thementaltheraphyhelthfinal.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTm {
    private String user_Id;
    private String name;
    private String address;
    private String jobRole;
    private String email;
    private String contact;
}
