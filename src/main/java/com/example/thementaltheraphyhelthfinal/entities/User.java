package com.example.thementaltheraphyhelthfinal.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_Id;

    private String name;
    private String address;
    private String jobRole;

    @Column(unique = true)
    private String email;

    private String contact;
    private String password;
}
