package com.example.PetShopAPI.controller;

import com.example.PetShopAPI.dto.AppointmentDTO;
import com.example.PetShopAPI.dto.AppointmentResponseDTO;
import com.example.PetShopAPI.dto.VaccinesDTO;
import com.example.PetShopAPI.dto.VaccinesResponseDTO;
import com.example.PetShopAPI.exceptions.ResourceNotFoundException;
import com.example.PetShopAPI.model.*;
import com.example.PetShopAPI.service.impl.DogService;
import com.example.PetShopAPI.service.impl.VaccinesService;
import com.example.PetShopAPI.service.impl.VeterinarianService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class VaccinesController {
    @Autowired
    public VaccinesService vaccinesService;

    @Autowired
    public VeterinarianService veterinarianService;

    @Autowired
    public DogService dogService;

    @PostMapping("/vaccines/save")
    @Transactional
    public VaccinesResponseDTO createVaccine(@RequestBody VaccinesDTO vaccinesDTO) throws SQLException {

        Dog dog = dogService.searchById(vaccinesDTO.getIdDog()).orElse(null);
        Veterinarian veterinarian = veterinarianService.searchById(vaccinesDTO.getIdVeterinarian()).orElse(null);

        Vaccines vaccines = new Vaccines(vaccinesDTO.getVaccineName(), vaccinesDTO.getDateVaccine(),
                dog, veterinarian);

        vaccinesService.save(vaccines);

        return vaccines.vaccinesResponseDTO();

    }

    @PutMapping("/vaccines/update")
    public ResponseEntity updateVaccine(@RequestBody Vaccines vaccines) throws SQLException {
        return ResponseEntity.ok(vaccinesService.update(vaccines));
    }

    @RequestMapping(value = "/vaccines", method = RequestMethod.GET, produces = "application/json")
    public List<Vaccines> getAllVaccines() throws SQLException {
        return vaccinesService.getAllResults();
    }

    @GetMapping("/vaccines/{id}")
    public ResponseEntity<Optional<Vaccines>> getVaccineById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            Optional<Vaccines> vaccines = vaccinesService.searchById(id);
            if (vaccines.isPresent()) {
                return ResponseEntity.ok(vaccines);
            }
            throw new ResourceNotFoundException("The vaccine with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching vaccine with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }

    @DeleteMapping("/vaccines/delete/{id}")
    public ResponseEntity deleteVaccine(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = vaccinesService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected vaccine has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The vaccine with id number " + id + "hasn't been found in the database.");
        }
    }

}
