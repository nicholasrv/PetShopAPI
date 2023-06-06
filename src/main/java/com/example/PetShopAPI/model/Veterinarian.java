package com.example.PetShopAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Veterinarian")
public class Veterinarian {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String regNumber;

    public Veterinarian(String firstName, String lastName, String regNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.regNumber = regNumber;
    }
}
