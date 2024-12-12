package com.backend.ticketsystem_backend_oop.service;

import com.backend.ticketsystem_backend_oop.model.Configuration;
import com.backend.ticketsystem_backend_oop.model.Customer;
import com.backend.ticketsystem_backend_oop.model.Ticket;
import com.backend.ticketsystem_backend_oop.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class TicketService {

    private Queue<Ticket> ticketPool = new LinkedList<>();
    private Object lock = new Object();
    private Thread vendorThread;
    private Thread customerThread;
    private Configuration configuration;
    private List<Vendor> vendors;
    private List<Customer> customers;

    @Autowired
    public TicketService(Configuration configuration) {
        this.configuration = configuration;
        this.lock = new Object();
        InitializeProcess();
    }

    private void InitializeProcess() {
        vendors = createVendors(configuration.getVendorCount());
        customers = createCustomers(configuration.getCustomerCount());

        ticketPool = new LinkedList<>();
        for (int i = 0; i < configuration.getTotalTicketsCount(); i++) {
            Vendor vendor = vendors.get(i % vendors.size());  // Round-robin vendor assignment
            Ticket ticket = new Ticket((long) i + 1, "Unsold", vendor);
            ticketPool.offer(ticket);
        }

        vendorThread = new Thread(new VendorService(configuration, vendors, lock, ticketPool));
        customerThread = new Thread(new CustomerService(configuration, customers, lock, ticketPool));
    }

    private List<Vendor> createVendors(int vendorCount) {
        List<Vendor> vendors = new LinkedList<>();
        for (int i = 0; i < vendorCount; i++) {
            vendors.add(new Vendor((long) (i + 1)));
        }
        return vendors;
    }

    private List<Customer> createCustomers(int customerCount) {
        List<Customer> customers = new LinkedList<>();
        for (int i = 0; i < customerCount; i++) {
            customers.add(new Customer((long) (i + 1)));
        }
        return customers;
    }

    public void startServices() {
        if (!vendorThread.isAlive()) {
            vendorThread.start();
        }
        if (!customerThread.isAlive()) {
            customerThread.start();
        }
    }

    public void stopServices() {
        if (vendorThread.isAlive()) {
            vendorThread.interrupt();
        }
        if (customerThread.isAlive()) {
            customerThread.interrupt();
        }
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        InitializeProcess();
    }
}