package com.example.PetShopAPI.controller;

import com.example.PetShopAPI.exceptions.BadRequestException;
import com.example.PetShopAPI.exceptions.ResourceNotFoundException;
import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Owner;
import com.example.PetShopAPI.service.impl.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class OwnerController {
    @Autowired
    public final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //POST
    @PostMapping("/owner/save")
    public ResponseEntity<?> saveNewOwner(@RequestBody Owner owner) throws BadRequestException {
        try {
            boolean doesThisOwnerExist = ownerService.existsOwnerBySsNumber(owner.getSsNumber());
            if (doesThisOwnerExist){
                return ResponseEntity.badRequest().body("This owner already exists on the database.");
            }
            return ResponseEntity.ok("Owner saved successfully!" + ownerService.save(owner));
        } catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("An error has occurred while trying to save this owner. Please contact our support team for further information.");
        }
    }

    ///UPDATE/PUT
    @PutMapping("/owner/update")
    public ResponseEntity updateOwner(@RequestBody Owner owner) throws SQLException {
        return ResponseEntity.ok(ownerService.update(owner));
    }

    // GET
    @RequestMapping(value = "/owner", method = RequestMethod.GET, produces = "application/json")
    public List<Owner> getAllOwners() throws SQLException{
        return ownerService.getAllResults();
    }


    // DELETE
    @DeleteMapping("/owner/delete/{id}")
    public ResponseEntity deleteOwner(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = ownerService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected owner has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The owner with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/owner/{id}")
    public ResponseEntity<Optional<Owner>> getOwnerById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<Owner> owner = ownerService.searchById(id);
            if(owner.isPresent()){
                return ResponseEntity.ok(owner);
            }
            throw new ResourceNotFoundException("The owner with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching owner with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }
}
