package com.example.PetShopAPI.dto;

import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Owner;
import com.example.PetShopAPI.model.Veterinarian;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class AppointmentResponseDTO {

    private Date dateAppointment;
    private LocalDateTime timeAppointment;
    private Veterinarian veterinarian;
    private Dog dog;
    private Owner owner;

    public AppointmentResponseDTO(Date dateAppointment, LocalDateTime timeAppointment, Veterinarian veterinarian, Dog dog, Owner owner) {
        this.dateAppointment = dateAppointment;
        this.timeAppointment = timeAppointment;
        this.veterinarian = veterinarian;
        this.dog = dog;
        this.owner = owner;
    }
}
