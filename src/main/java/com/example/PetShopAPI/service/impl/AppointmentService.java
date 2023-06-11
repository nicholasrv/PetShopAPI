package com.example.PetShopAPI.service.impl;

import com.example.PetShopAPI.exceptions.ResourceNotFoundException;
import com.example.PetShopAPI.model.Appointment;
import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Veterinarian;
import com.example.PetShopAPI.repository.AppointmentRepository;
import com.example.PetShopAPI.repository.DogRepository;
import com.example.PetShopAPI.repository.VeterinarianRepository;
import com.example.PetShopAPI.service.PetShopApiService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements PetShopApiService<Appointment> {

    public final AppointmentRepository appointmentRepository;

    public final DogRepository dogRepository;

    public final VeterinarianRepository veterinarianRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DogRepository dogRepository, VeterinarianRepository veterinarianRepository) {
        this.appointmentRepository = appointmentRepository;
        this.dogRepository = dogRepository;
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        if(appointment != null){
            return appointmentRepository.save(appointment);
        }
        return new Appointment();
    }

    @Override
    public String update(Appointment appointment) {
        if(appointment != null && appointmentRepository.findById(appointment.getId()).isPresent()){
            appointmentRepository.saveAndFlush(appointment);
            return "Appointment updated successfully!";
        }
        return "Sorry, but the selected appointment couldn't be updated";
    }

    @Override
    public List<Appointment> getAllResults() throws SQLException {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> searchById(Long id) throws SQLException {
        return appointmentRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(appointmentRepository.findById(id).isPresent()){
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Appointment> findAppointmentByDog(Long id) throws ResourceNotFoundException {
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There weren't found any appointments for this dog."));
        return appointmentRepository.findByDog(dog);
    }

    public List<Appointment> findAppointmentByVeterinarian(Long id) throws ResourceNotFoundException {
        Veterinarian veterinarian = veterinarianRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There weren't found any appointments for this veterinarian."));
        return appointmentRepository.findByVeterinarian(veterinarian);
    }
}
