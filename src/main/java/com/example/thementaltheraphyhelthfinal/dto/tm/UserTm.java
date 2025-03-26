package com.example.thementaltheraphyhelthfinal.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTm {
    private int user_Id;
    private String name;
    private String address;
    private String jobRole;
    private String email;
    private String contact;
    private String password;
}
