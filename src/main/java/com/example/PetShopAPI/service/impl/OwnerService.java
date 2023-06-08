package com.example.PetShopAPI.service.impl;

import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Owner;
import com.example.PetShopAPI.repository.DogRepository;
import com.example.PetShopAPI.repository.OwnerRepository;
import com.example.PetShopAPI.service.PetShopApiService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService implements PetShopApiService<Owner> {
    public final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner save(Owner owner) {
        if (owner != null) {
            return ownerRepository.save(owner);
        }
        return new Owner();
    }

    @Override
    public String update(Owner owner) {
        if (owner != null && ownerRepository.findById(owner.getId()).isPresent()){
            ownerRepository.saveAndFlush(owner);
            return "Owner updated successfully!";
        }
        return "Sorry, but the selected owner couldn't be updated";
    }

    @Override
    public List<Owner> getAllResults() throws SQLException {
        return ownerRepository.findAll();
    }

    @Override
    public Optional<Owner> searchById(Long id) throws SQLException {
        return ownerRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(ownerRepository.findById(id).isPresent()){
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
