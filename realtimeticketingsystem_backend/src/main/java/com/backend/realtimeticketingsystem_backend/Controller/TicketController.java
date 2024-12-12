package com.backend.realtimeticketingsystem_backend.Controller;

import com.backend.realtimeticketingsystem_backend.model.Configuration;
import com.backend.realtimeticketingsystem_backend.model.Customer;
import com.backend.realtimeticketingsystem_backend.model.Vendor;
import com.backend.realtimeticketingsystem_backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketing")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // POST endpoint to add a new vendor
    @PostMapping("/vendors")
    public String addVendor(@RequestBody Vendor vendor) {
        try {
            ticketService.AddVendor(vendor);
            return ("Successfully Added Vendor");
        } catch (Exception e) {
            return "Error adding vendor: " + e.getMessage();
        }
    }

    // POST endpoint to add a new customer
    @PostMapping("/customers")
    public String addCustomer(@RequestBody Customer customer) {
        try {
            ticketService.AddCustomer(customer);
            return ("Successfully Added Customer");
        } catch (Exception e) {
            return "Error adding customer: " + e.getMessage();
        }
    }

    // POST endpoint to save the system configuration
    @PostMapping("/configuration")
    public String saveConfiguration(@RequestBody Configuration configuration) {
        try {
            ticketService.saveConfiguration(configuration);
            return "Configuration saved successfully!";
        } catch (Exception e) {
            return "Error saving configuration: " + e.getMessage();
        }
    }

    // GET endpoint to retrieve the current system configuration
    @GetMapping("/configuration")
    public Configuration getConfiguration() {
        return ticketService.getConfiguration();
    }

    // POST endpoint to start the system (vendor and customer threads)
    @PostMapping("/start")
    public String startSystem() {
        try {
            ticketService.startSystem();
            return "System started successfully!";
        } catch (Exception e) {
            return "Error starting system: " + e.getMessage();
        }
    }
}
