package com.example.PetShopAPI.model;


import com.example.PetShopAPI.dto.AppointmentResponseDTO;
import com.example.PetShopAPI.dto.DogDTO;
import com.example.PetShopAPI.dto.OwnerDTO;
import com.example.PetShopAPI.dto.VeterinarianDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    private LocalDate dateAppointment;
    private LocalDateTime timeAppointment;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Dog dog;

    @ManyToOne
    private Veterinarian veterinarian;

    public Appointment(LocalDate dateAppointment, LocalDateTime timeAppointment, Owner owner, Dog dog, Veterinarian veterinarian) {
        this.dateAppointment = dateAppointment;
        this.timeAppointment = timeAppointment;
        this.owner = owner;
        this.dog = dog;
        this.veterinarian = veterinarian;
    }

    public AppointmentResponseDTO responseDTO(){

        VeterinarianDTO veterinarianDTO = new VeterinarianDTO(this.veterinarian.getFirstName(), this.veterinarian.getLastName());
        DogDTO dogDTO = new DogDTO(this.dog.getNickName());
        OwnerDTO ownerDTO = new OwnerDTO(this.owner.getFirstName(), this.owner.getLastName());

        return new AppointmentResponseDTO(
                this.dateAppointment,
                this.timeAppointment,
                veterinarianDTO,
                dogDTO,
                ownerDTO
        );

    }
}
