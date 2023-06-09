package com.example.PetShopAPI.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Dog")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nickName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dischargeDate;

    @ManyToOne
    private Owner owner;

    public Dog(String nickName, LocalDateTime dischargeDate, Owner owner) {
        this.nickName = nickName;
        this.dischargeDate = dischargeDate;
        this.owner = owner;
    }
}
