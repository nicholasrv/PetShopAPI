package com.example.PetShopAPI.dto;

import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Owner;
import com.example.PetShopAPI.model.Veterinarian;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AppointmentResponseDTO {

    private LocalDate dateAppointment;
    private LocalDateTime timeAppointment;
    private Veterinarian veterinarian;
    private Dog dog;
    private Owner owner;

    public AppointmentResponseDTO(LocalDate dateAppointment, LocalDateTime timeAppointment, VeterinarianDTO veterinarianDTO, DogDTO dogDTO, OwnerDTO ownerDTO) {
    }
}
