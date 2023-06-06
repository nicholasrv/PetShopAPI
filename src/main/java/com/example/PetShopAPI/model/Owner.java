package com.example.PetShopAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String ssNumber;

    public Owner(String firstName, String lastName, String address, String ssNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.ssNumber = ssNumber;
    }
}
