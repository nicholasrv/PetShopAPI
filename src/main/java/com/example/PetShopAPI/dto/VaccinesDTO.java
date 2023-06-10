package com.example.PetShopAPI.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinesDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVaccine;

    private String vaccineName;
    private Long idVeterinarian;
    private Long idDog;
}
