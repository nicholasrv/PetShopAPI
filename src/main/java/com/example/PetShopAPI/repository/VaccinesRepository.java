package com.example.PetShopAPI.repository;

import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Vaccines;
import com.example.PetShopAPI.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinesRepository extends JpaRepository<Vaccines, Long> {
    List<Vaccines> findByDog (Dog dog);
    List<Vaccines> findByVeterinarian (Veterinarian veterinarian);
}
