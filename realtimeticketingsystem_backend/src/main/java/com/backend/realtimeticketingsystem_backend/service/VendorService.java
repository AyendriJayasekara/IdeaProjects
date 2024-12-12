package com.backend.realtimeticketingsystem_backend.service;

import com.backend.realtimeticketingsystem_backend.model.Ticket;
import com.backend.realtimeticketingsystem_backend.model.Vendor;
import com.backend.realtimeticketingsystem_backend.model.Configuration;
import com.backend.realtimeticketingsystem_backend.Repository.TicketRepository;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class VendorService implements Runnable {

    private final Configuration configuration;
    private final List<Vendor> vendors;
    private final TicketRepository ticketRepository;
    private final AtomicInteger ticketstore;
    private final Object lock;

    public VendorService(Configuration configuration, List<Vendor> vendors, TicketRepository ticketRepository, AtomicInteger ticketstore, Object lock) {
        this.configuration = configuration;
        this.vendors = vendors;
        this.ticketRepository = ticketRepository;
        this.ticketstore = ticketstore;
        this.lock = lock;
    }

    @Override
    public void run() {
        vendors.forEach(vendor -> {
            synchronized (lock) { // Synchronize on the lock object
                for (int i = 0; i < configuration.getTotalTicketsCount(); i++) {
                    if (ticketstore.get() >= configuration.getMaxTicketCapacity()) {
                        try {
                            lock.wait(); // Wait if ticket store is full
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    Ticket ticket = new Ticket();
                    ticket.setStatus("UNSOLD");
                    ticket.setVendor(vendor);
                    ticketRepository.save(ticket);

                    ticketstore.incrementAndGet();

                    System.out.println("Ticket released by Vendor [ID: " + vendor.getId() + ", Name: " + vendor.getName() + "] - Ticket " + (i + 1));

                    lock.notifyAll(); // Notify all waiting threads

                    try {
                        Thread.sleep(1000 / configuration.getTicketReleaseRate());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
    }
}