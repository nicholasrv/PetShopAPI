package com.example.PetShopAPI.controller;

import com.example.PetShopAPI.dto.AppointmentDTO;
import com.example.PetShopAPI.dto.AppointmentResponseDTO;
import com.example.PetShopAPI.dto.VeterinarianDTO;
import com.example.PetShopAPI.exceptions.ResourceNotFoundException;
import com.example.PetShopAPI.model.Appointment;
import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Owner;
import com.example.PetShopAPI.model.Veterinarian;
import com.example.PetShopAPI.service.impl.AppointmentService;
import com.example.PetShopAPI.service.impl.DogService;
import com.example.PetShopAPI.service.impl.OwnerService;
import com.example.PetShopAPI.service.impl.VeterinarianService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DogService dogService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private VeterinarianService veterinarianService;

    @PostMapping("/appointments/save")
    @Transactional
    public AppointmentResponseDTO createAppointment(@RequestBody AppointmentDTO appointmentDTO) throws SQLException {

        // Since an appointment is built up by 3 attributes (A dog, an owner and a vet), we need to first of all create an instance of each one of these entities.
        // To avoid handling/showing up sensitive/unnecessary information on our view layer, we call the methods of each service but receiving DTOs with getters as our parameters.
        // By doing that, we're making a search into each entity and storing the results in their DTOs, respectively.

        Dog dog = dogService.searchById(appointmentDTO.getIdDog()).orElse(null);
        Owner owner = ownerService.searchById(appointmentDTO.getIdOwner()).orElse(null);
        Veterinarian veterinarian = veterinarianService.searchById(appointmentDTO.getIdVeterinarian()).orElse(null);

        // Then, in the next line we'll create an instance of the appointment itself, bringing all the information together (date, time and all involved entities, which are the dog, it's owner and the vet)
        Appointment appointment = new Appointment(appointmentDTO.getDateAppointment(), appointmentDTO.getTimeAppointment(),
                owner, dog, veterinarian);

        // Then, finally, we call whatever method we've built on our service layer to save the object on the DB.
        appointmentService.save(appointment);

        //Here, I'll return the object through a method called responseDTO modeled on my Appointment Entity, that returns an instance of my AppointmentResponseDTO class.
        return appointment.responseDTO();

        //The biggest advantage of the above logic, is that we're respecting and following the encapsulation principle strictly, leaving no loose ends that could eventually implicate on data exposure/leak.
    }

    @PutMapping("/appointments/update")
    public ResponseEntity updateAppointment(@RequestBody Appointment appointment) throws SQLException {
        return ResponseEntity.ok(appointmentService.update(appointment));
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.GET, produces = "application/json")
    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentService.getAllResults();
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointmentById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            Optional<Appointment> appointment = appointmentService.searchById(id);
            if (appointment.isPresent()) {
                return ResponseEntity.ok(appointment);
            }
            throw new ResourceNotFoundException("The appointment with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching appointment with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }

    @DeleteMapping("/appointments/delete/{id}")
    public ResponseEntity deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = appointmentService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected appointment has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The appointment with id number " + id + "hasn't been found in the database.");
        }
    }





}
