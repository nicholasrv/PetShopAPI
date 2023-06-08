package com.example.PetShopAPI.controller;

import com.example.PetShopAPI.exceptions.BadRequestException;
import com.example.PetShopAPI.exceptions.ResourceNotFoundException;
import com.example.PetShopAPI.model.Owner;
import com.example.PetShopAPI.model.Veterinarian;
import com.example.PetShopAPI.service.impl.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class VeterinarianController {
    @Autowired
    public final VeterinarianService veterinarianService;

    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    //POST
    @PostMapping("/veterinarian/save")
    public ResponseEntity<?> saveNewVeterinarian(@RequestBody Veterinarian veterinarian) throws BadRequestException {
        try {
            boolean doesThisVeterinarianExist = veterinarianService.existsVeterinarianByRegNumber(veterinarian.getRegNumber());
            if (doesThisVeterinarianExist){
                return ResponseEntity.badRequest().body("This vet already exists on the database.");
            }
            return ResponseEntity.ok("Vet saved successfully!" + veterinarianService.save(veterinarian));
        } catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("An error has occurred while trying to save this vet. Please contact our support team for further information.");
        }
    }

    ///UPDATE/PUT
    @PutMapping("/veterinarian/update")
    public ResponseEntity updateVeterinarian(@RequestBody Veterinarian veterinarian) throws SQLException {
        return ResponseEntity.ok(veterinarianService.update(veterinarian));
    }

    // GET
    @RequestMapping(value = "/veterinarian", method = RequestMethod.GET, produces = "application/json")
    public List<Veterinarian> getAllVeterinarians() throws SQLException{
        return veterinarianService.getAllResults();
    }


    // DELETE
    @DeleteMapping("/veterinarian/delete/{id}")
    public ResponseEntity deleteVeterinarian(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = veterinarianService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected veterinarian has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The veterinarian with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/veterinarian/{id}")
    public ResponseEntity<Optional<Veterinarian>> getVeterinarianById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<Veterinarian> veterinarian = veterinarianService.searchById(id);
            if(veterinarian.isPresent()){
                return ResponseEntity.ok(veterinarian);
            }
            throw new ResourceNotFoundException("The veterinarian with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching veterinarian with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }


























}
