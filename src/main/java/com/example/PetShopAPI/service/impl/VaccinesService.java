package com.example.PetShopAPI.service.impl;

import com.example.PetShopAPI.exceptions.ResourceNotFoundException;
import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Vaccines;
import com.example.PetShopAPI.model.Veterinarian;
import com.example.PetShopAPI.repository.DogRepository;
import com.example.PetShopAPI.repository.VaccinesRepository;
import com.example.PetShopAPI.repository.VeterinarianRepository;
import com.example.PetShopAPI.service.PetShopApiService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinesService implements PetShopApiService<Vaccines> {
    public final VaccinesRepository vaccinesRepository;

    public final DogRepository dogRepository;

    public final VeterinarianRepository veterinarianRepository;

    public VaccinesService(VaccinesRepository vaccinesRepository, DogRepository dogRepository, VeterinarianRepository veterinarianRepository) {
        this.vaccinesRepository = vaccinesRepository;
        this.dogRepository = dogRepository;
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public Vaccines save(Vaccines vaccines) {
        if(vaccines != null){
            return vaccinesRepository.save(vaccines);
        };
        return new Vaccines();
    };

    @Override
    public String update(Vaccines vaccines) {
        if(vaccines != null && vaccinesRepository.findById(vaccines.getId()).isPresent()){
            vaccinesRepository.saveAndFlush(vaccines);
            return "Vaccine updated successfully!";
        };
        return "Sorry, but the selected vaccine couldn't be updated";
    }

    @Override
    public List<Vaccines> getAllResults() throws SQLException {
        return vaccinesRepository.findAll();
    }

    @Override
    public Optional<Vaccines> searchById(Long id) throws SQLException {
        return vaccinesRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(vaccinesRepository.findById(id).isPresent()){
            vaccinesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Vaccines> findVaccinesByDog(Long id) throws ResourceNotFoundException {
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The selected dog wasn't found on the database."));
        return vaccinesRepository.findByDog(dog);
    }

    public List<Vaccines> findVaccinesByVeterinarian(Long id) throws ResourceNotFoundException {
        Veterinarian veterinarian = veterinarianRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The selected veterinarian wasn't found on the database."));
        return vaccinesRepository.findByVeterinarian(veterinarian);
    }
}
