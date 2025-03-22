package com.example.thementaltheraphyhelthfinal.entities.Associatentities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class sessionIds {
    private String therapist;
    private String patient;
}
