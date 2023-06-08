package com.example.PetShopAPI.service.impl;

import com.example.PetShopAPI.model.Appointment;
import com.example.PetShopAPI.repository.AppointmentRepository;
import com.example.PetShopAPI.service.PetShopApiService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements PetShopApiService<Appointment> {

    public final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        if(appointment != null){
            return appointmentRepository.save(appointment);
        };
        return new Appointment();
    };

    @Override
    public String update(Appointment appointment) {
        if(appointment != null && appointmentRepository.findById(appointment.getId()).isPresent()){
            appointmentRepository.saveAndFlush(appointment);
            return "Appointment updated successfully!";
        };
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
}
