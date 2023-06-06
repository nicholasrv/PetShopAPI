package com.example.PetShopAPI.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateAppointment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeAppointment;

    private Long idVeterinarian;
    private Long idDog;
    private Long idOwner;
}
