package com.example.thementaltheraphyhelthfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int user_Id;
    private String name;
    private String address;
    private String jobRole;
    private String email;
    private String contact;
    private String password;
}
