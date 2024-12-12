package com.example.OOP_.ticketing_system.controller;

import com.example.OOP_.ticketing_system.model.Ticket;
import com.example.OOP_.ticketing_system.repository.TicketRepository;
import com.example.OOP_.ticketing_system.service.TicketService;
import com.example.OOP_.ticketing_system.service.Ticketpool;
import com.example.OOP_.ticketing_system.service.VendorService;
import com.example.OOP_.ticketing_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

//@RequestMapping ("/app")
public class TicketController {

    @Autowired
   private TicketRepository repository ;
   Ticketpool ticketpool = new Ticketpool(repository);
   VendorService vendorService = new VendorService(ticketpool);
   CustomerService customerService = new CustomerService(ticketpool);
    @PostMapping("/startVendor")
    public void startVendor(@RequestParam int totalTickets, @RequestParam long releaseInterval) {
        vendorService.startSellingTickets(totalTickets, releaseInterval);
    }

    @PostMapping("/startCustomer")
    public void startCustomer(@RequestParam long retrievalInterval, @RequestParam int ticketsPerPurchase) {
        customerService.startBuyingTickets(retrievalInterval, ticketsPerPurchase);
    }
}
