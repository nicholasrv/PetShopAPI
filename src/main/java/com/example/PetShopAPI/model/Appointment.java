package com.example.PetShopAPI.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date dateAppointment;
    private LocalDateTime timeAppointment;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Dog dog;

    @ManyToOne
    private Veterinarian veterinarian;

    public Appointment(Date dateAppointment, LocalDateTime timeAppointment, Owner owner, Dog dog, Veterinarian veterinarian) {
        this.dateAppointment = dateAppointment;
        this.timeAppointment = timeAppointment;
        this.owner = owner;
        this.dog = dog;
        this.veterinarian = veterinarian;
    }
}
