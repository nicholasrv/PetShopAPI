package com.example.PetShopAPI.dto;

import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Veterinarian;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinesResponseDTO {

    private LocalDate dateVaccine;
    private String vaccineName;
    private Veterinarian veterinarian;
    private Dog dog;

    public VaccinesResponseDTO(LocalDate dateVaccine, String vaccineName, VeterinarianDTO veterinarianDTO, DogDTO dogDTO) {
    }
}
