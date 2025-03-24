package com.example.thementaltheraphyhelthfinal.dto;

import jakarta.persistence.Column;
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
public class UserDto {
    @Id
    private String user_Id;

    private String name;
    private String address;
    private String jobRole;

    @Column(unique = true)
    private String email;

    private String contact;

//    @OneToMany(mappedBy = "user")
//    private List<Patient> patients;
}
