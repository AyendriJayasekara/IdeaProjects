package com.backend.realtimeticketingsystem_backend.exception;

public class VendorNotFoundException extends RuntimeException {
    public VendorNotFoundException(String message) {
        super(message);  // Pass the message to the parent class (RuntimeException)
    }
}