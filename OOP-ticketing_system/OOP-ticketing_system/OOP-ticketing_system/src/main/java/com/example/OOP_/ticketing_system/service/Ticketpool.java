package com.example.OOP_.ticketing_system.service;
import com.example.OOP_.ticketing_system.model.Ticket;
import com.example.OOP_.ticketing_system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

@Service
public class Ticketpool {
    private final TicketRepository ticketRepository;
    private final Logger logger = Logger.getLogger(Ticketpool.class.getName());
    private final ConcurrentLinkedQueue<Ticket> ticketsQueue = new ConcurrentLinkedQueue<>();
    private int maxTicketCapacity;

    @Autowired
    public Ticketpool(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public synchronized void addTickets(Ticket ticket) throws InterruptedException {
        while (ticketRepository.count() >= maxTicketCapacity) {
            wait();
        }
        ticketRepository.save(ticket);
        notifyAll();
    }

    public synchronized Ticket buyTicket() throws InterruptedException {
        while (ticketRepository.count() == 0) {
            wait();
        }
        Ticket ticket = ticketRepository.findAll().get(0); // Or a more sophisticated way to get a ticket
        ticketRepository.delete(ticket);
        notifyAll();
        logger.info("Ticket bought. Remaining tickets in the pool: " + ticketRepository.count());
        return ticket;
    }
}
