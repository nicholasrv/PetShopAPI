package com.example.PetShopAPI.controller;

import com.example.PetShopAPI.exceptions.BadRequestException;
import com.example.PetShopAPI.exceptions.ResourceNotFoundException;
import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.service.impl.DogService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class DogController {
    @Autowired
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    //POST
    @PostMapping("/dog/save")
    public ResponseEntity<?> saveNewDog(@RequestBody Dog dog) throws BadRequestException {
        try {
            boolean doesThisDogExist = dogService.existsDogByNickName(dog.getNickName());
            if (doesThisDogExist){
                return ResponseEntity.badRequest().body("This dog already exists on the database.");
            }
            return ResponseEntity.ok("Dog saved successfully!" + dogService.save(dog));
        } catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("An error has occurred while trying to save this dog. Please contact our support team for further information.");
        }
    }

    ///UPDATE/PUT
    @PutMapping("/dog/update")
    public ResponseEntity updateDog(@RequestBody Dog dog) throws SQLException {
        return ResponseEntity.ok(dogService.update(dog));
    }

    // GET
    @RequestMapping(value = "/dog", method = RequestMethod.GET, produces = "application/json")
    public List<Dog> getAllDogs() throws SQLException{
        return dogService.getAllResults();
    }


    // DELETE
    @DeleteMapping("/dog/delete/{id}")
    public ResponseEntity deleteDog(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = dogService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected dog has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The dog with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/dog/{id}")
    public ResponseEntity<Optional<Dog>> getDogById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<Dog> dog = dogService.searchById(id);
            if(dog.isPresent()){
                return ResponseEntity.ok(dog);
            }
            throw new ResourceNotFoundException("The dog with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching dog with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }
}
