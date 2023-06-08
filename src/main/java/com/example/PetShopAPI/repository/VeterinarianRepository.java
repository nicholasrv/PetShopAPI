package com.example.PetShopAPI.repository;

import com.example.PetShopAPI.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
}
