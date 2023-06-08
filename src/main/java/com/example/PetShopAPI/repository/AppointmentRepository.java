package com.example.PetShopAPI.repository;

import com.example.PetShopAPI.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
