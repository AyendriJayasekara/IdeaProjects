

package com.backend.realtimeticketingsystem_backend.service;

import com.backend.realtimeticketingsystem_backend.model.Ticket;
import com.backend.realtimeticketingsystem_backend.model.Customer;
import com.backend.realtimeticketingsystem_backend.model.Configuration;
import com.backend.realtimeticketingsystem_backend.Repository.TicketRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class CustomerService implements Runnable {

    private final Configuration configuration;
    private final List<Customer> customers;
    private final TicketRepository ticketRepository;
    private final AtomicInteger ticketstore;
    private final Object lock;

    public CustomerService(Configuration configuration, List<Customer> customers, TicketRepository ticketRepository, AtomicInteger ticketstore, Object lock) {
        this.configuration = configuration;
        this.customers = customers;
        this.ticketRepository = ticketRepository;
        this.ticketstore = ticketstore;
        this.lock = lock;
    }

    @Override
    public void run() {
        customers.forEach(customer -> {
            synchronized (lock) { // Synchronize on the lock object
                for (int i = 0; i < configuration.getTicketPurchaseRate(); i++) {
                    if (ticketstore.get() <= 0) {
                        try {
                            lock.wait(); // Wait if no tickets are available
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    Ticket ticket = ticketRepository.findFirstByStatus("UNSOLD");
                    if (ticket != null) {
                        ticket.setStatus("SOLD");
                        ticket.setCustomer(customer);
                        ticketRepository.save(ticket);
                        ticketstore.decrementAndGet();

                        System.out.println("Ticket purchased by Customer [ID: " + customer.getId() + ", Name: " + customer.getName() + "] - Ticket " + ticket.getId());

                        lock.notifyAll(); // Notify all waiting threads

                        try {
                            Thread.sleep(1000 / configuration.getTicketPurchaseRate());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });
    }
}