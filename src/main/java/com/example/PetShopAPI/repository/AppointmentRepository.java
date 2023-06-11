package com.example.PetShopAPI.repository;

import com.example.PetShopAPI.model.Appointment;
import com.example.PetShopAPI.model.Dog;
import com.example.PetShopAPI.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDog(Dog dog);
    List<Appointment> findByVeterinarian(Veterinarian veterinarian);

}
