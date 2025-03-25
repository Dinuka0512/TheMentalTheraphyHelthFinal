package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String user_Id;

    private String name;
    private String address;
    private String jobRole;

    @Column(unique = true)
    private String email;

    private String contact;
    private String password;
}
