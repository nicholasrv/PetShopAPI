package com.example.PetShopAPI.service.impl;

import com.example.PetShopAPI.model.Appointment;
import com.example.PetShopAPI.model.Vaccines;
import com.example.PetShopAPI.repository.VaccinesRepository;
import com.example.PetShopAPI.service.PetShopApiService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinesService implements PetShopApiService<Vaccines> {
    public final VaccinesRepository vaccinesRepository;

    public VaccinesService(VaccinesRepository vaccinesRepository) {
        this.vaccinesRepository = vaccinesRepository;
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

}
