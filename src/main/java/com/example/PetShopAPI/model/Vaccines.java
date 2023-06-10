package com.example.PetShopAPI.model;

import com.example.PetShopAPI.dto.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Vaccines")
public class Vaccines {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "vaccineName")
    public String vaccineName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate dateVaccine;

    @ManyToOne
    public Dog dog;

    @ManyToOne
    public Veterinarian veterinarian;

    public Vaccines(String vaccineName, LocalDate dateVaccine, Dog dog, Veterinarian veterinarian) {
        this.vaccineName = vaccineName;
        this.dateVaccine = dateVaccine;
        this.dog = dog;
        this.veterinarian = veterinarian;
    }

    public VaccinesResponseDTO vaccinesResponseDTO(){

        VeterinarianDTO veterinarianDTO = new VeterinarianDTO(this.veterinarian.getFirstName(), this.veterinarian.getLastName());
        DogDTO dogDTO = new DogDTO(this.dog.getNickName());

        return new VaccinesResponseDTO(
                this.dateVaccine,
                this.vaccineName,
                veterinarianDTO,
                dogDTO
        );
    }
}
