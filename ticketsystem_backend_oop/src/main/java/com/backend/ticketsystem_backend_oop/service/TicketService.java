package com.backend.ticketsystem_backend_oop.service;

import com.backend.ticketsystem_backend_oop.model.Configuration;
import com.backend.ticketsystem_backend_oop.model.Customer;
import com.backend.ticketsystem_backend_oop.model.Ticket;
import com.backend.ticketsystem_backend_oop.model.Vendor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
@ComponentScan
@Service
public class TicketService {

    private Queue<Ticket> ticketPool = new LinkedList<>();
    private Object lock = new Object();
    private Thread vendorThread;
    private Thread customerThread;
    private Configuration configuration;
    private List<Vendor> vendors;
    private List<Customer> customers;


    public TicketService(Configuration pastConfiguration) {
        this.configuration = pastConfiguration;
        this.lock = new Object();
        Initializeprocess();
    }

    private void Initializeprocess() {
        // based on the configuration initialize customer and vendors
        vendors = CreateVendors(configuration.getVendorcount());
        customers = CreateCustomers(configuration.getCustomercount());

        // based on the configuration initialize ticketpool
        ticketPool = new LinkedList<>();
        for (int i = 0; i < configuration.getTotalTicketsCount(); i++) {
            // Add a vendor from the list (round-robin style)
            Vendor vendor = vendors.get(i % vendors.size());  // Use modulo to avoid index out-of-bounds
            Ticket ticket = new Ticket((long)i + 1, "Unsold", vendor);
            ticketPool.offer(ticket);  // Add the ticket to the ticket pool
        }

        // Start the Vendor and Customer threads
        vendorThread = new Thread(new VendorService(configuration, vendors, lock, ticketPool));
        customerThread = new Thread(new CustomerService(configuration, customers, lock, ticketPool));
    }

    //  based on the vendor count create list of vendors
    private List<Vendor> CreateVendors(int vendorCount) {
        List<Vendor> vendors = new LinkedList<>();
        for (int i = 0; i < vendorCount; i++) {
            vendors.add(new Vendor((long) (i + 1)));
        }
        return vendors;
    }

    // based on the customer count create list of customers
    private List<Customer> CreateCustomers(int customerCount) {
        List<Customer> customers = new LinkedList<>();
        for (int i = 0; i < customerCount; i++) {
            customers.add(new Customer((long) (i + 1)));  // Simple customer ID assignment
        }
        return customers;
    }

    // Start the vendor and customer services (run the threads)
    public void StartServices() {
        if (!vendorThread.isAlive()) {
            vendorThread.start();
        }
        if (!customerThread.isAlive()) {
            customerThread.start();
        }
    }

    // Stop the vendor and customer services (interrupt the threads)
    public void StopServices() {
        if (vendorThread.isAlive()) {
            vendorThread.interrupt();
        }
        if (customerThread.isAlive()) {
            customerThread.interrupt();
        }
    }

    public void SetConfiguration(Configuration configuration) {
        this.configuration = configuration;
        Initializeprocess();
    }
}
