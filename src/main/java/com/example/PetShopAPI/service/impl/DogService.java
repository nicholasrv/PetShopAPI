package com.example.PetShopAPI.service.impl;


import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Veterinarian;
import com.example.PetShopAPI.repository.DogRepository;
import com.example.PetShopAPI.service.PetShopApiService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class DogService implements PetShopApiService<Dog> {

    public final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog save(Dog dog) {
        if (dog != null) {
            return dogRepository.save(dog);
        }
        return new Dog();
    }

    @Override
    public String update(Dog dog) {
        if (dog != null && dogRepository.findById(dog.getId()).isPresent()){
            dogRepository.saveAndFlush(dog);
            return "Dog updated successfully!";
        }
        return "Sorry, but the selected dog couldn't be updated";
    }

    @Override
    public List<Dog> getAllResults() throws SQLException {
        return dogRepository.findAll();
    }

    @Override
    public Optional<Dog> searchById(Long id) throws SQLException {
        return dogRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(dogRepository.findById(id).isPresent()){
            dogRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsDogByNickName(String nickName) {
        return dogRepository.existsDogByNickName(nickName);
    }
}
