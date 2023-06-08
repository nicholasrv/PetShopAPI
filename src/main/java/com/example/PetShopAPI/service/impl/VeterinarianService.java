package com.example.PetShopAPI.service.impl;

import com.example.PetShopAPI.model.Veterinarian;
import com.example.PetShopAPI.repository.VeterinarianRepository;
import com.example.PetShopAPI.service.PetShopApiService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class VeterinarianService implements PetShopApiService<Veterinarian> {

    public final VeterinarianRepository veterinarianRepository;

    public VeterinarianService(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public Veterinarian save(Veterinarian veterinarian) {
        if (veterinarian != null) {
            return veterinarianRepository.save(veterinarian);
        }
        return new Veterinarian();
    }

    @Override
    public String update(Veterinarian veterinarian) {
        if (veterinarian != null && veterinarianRepository.findById(veterinarian.getId()).isPresent()){
            veterinarianRepository.saveAndFlush(veterinarian);
            return "Veterinarian updated successfully!";
        }
        return "Sorry, but the selected veterinarian couldn't be updated";
    }

    @Override
    public List<Veterinarian> getAllResults() throws SQLException {
        return veterinarianRepository.findAll();
    }

    @Override
    public Optional<Veterinarian> searchById(Long id) throws SQLException {
        return veterinarianRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(veterinarianRepository.findById(id).isPresent()){
            veterinarianRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsVeterinarianByRegNumber(String regNumber){
        return veterinarianRepository.existsVeterinarianByRegNumber(regNumber);
    }
}
