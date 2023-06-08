package com.example.PetShopAPI.repository;

import com.example.PetShopAPI.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
    boolean existsDogByNickName(String nickName);
}
