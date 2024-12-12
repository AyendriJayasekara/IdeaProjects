package com.example.OOP_.ticketing_system.service;

import com.example.OOP_.ticketing_system.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    private  Ticketpool ticketpool;
    private int totalTickets;
    private long releaseInterval;
    private long perTicketDelay = 500;

    @Autowired
    public VendorService(Ticketpool ticketPool){
        this.ticketpool = ticketPool;
    }
    public void startSellingTickets(int totalTickets, long releaseInterval) {
        this.totalTickets = totalTickets;
        this.releaseInterval = releaseInterval;
        new Thread(this::runVendor).start();
    }

    private void runVendor() {
        for (int i = 1; i <= totalTickets; i++) {
            try {
                Ticket ticket = new Ticket();
                ticket.setVendorName("Vendor1");
                ticket.setStatus("Available");
                ticketpool.addTickets(ticket);
                Thread.sleep(perTicketDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
