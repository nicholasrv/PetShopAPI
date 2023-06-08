package com.example.PetShopAPI.exceptions;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
