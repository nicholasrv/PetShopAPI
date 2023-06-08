package com.example.PetShopAPI.repository;

import com.example.PetShopAPI.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsOwnerBySsNumber(String ssNumber);
}
