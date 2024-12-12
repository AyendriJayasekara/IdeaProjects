package com.example.OOP_.ticketing_system.service;

import com.example.OOP_.ticketing_system.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private Ticketpool ticketpool;
    private long customerRetrievalRate;
    private int ticketsPerPurchase;
    private long perTicketDelay = 800;

    @Autowired
    public CustomerService(Ticketpool ticketpool) {
        this.ticketpool = ticketpool;
    }

    public void startBuyingTickets(long retrievalInterval, int ticketsPerPurchase) {
        this.customerRetrievalRate = retrievalInterval;
        this.ticketsPerPurchase = ticketsPerPurchase;
        new Thread(this::runCustomer).start();
    }

    private void runCustomer() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (int i = 0; i < ticketsPerPurchase; i++) {
                    ticketpool.buyTicket();
                    Thread.sleep(perTicketDelay);
                }
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
