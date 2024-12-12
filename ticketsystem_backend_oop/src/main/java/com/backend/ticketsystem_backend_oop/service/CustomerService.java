package com.backend.ticketsystem_backend_oop.service;

import com.backend.ticketsystem_backend_oop.model.Customer;
import com.backend.ticketsystem_backend_oop.model.Ticket;
import org.springframework.stereotype.Service;


import com.backend.ticketsystem_backend_oop.model.Configuration;

import java.util.List;
import java.util.Queue;

public class CustomerService implements Runnable {


        private final Configuration configuration;
        private final List<Customer> customers;
        private final Object lock;
        private final Queue<Ticket> ticketPool;


        public CustomerService(Configuration configuration, List<Customer> customers, Object lock, Queue<Ticket> ticketPool ) {
            this.configuration = configuration;
            this.customers = customers;
            this.lock = lock;
            this.ticketPool = ticketPool;
        }


        @Override
        public void run(){
            customers.forEach(customer -> {
                synchronized (lock){

                    for(int i =0 ; i < configuration.getTicketPurchaseRate(); i++ ){
                        if (ticketPool.isEmpty()){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }

                    }

                    Ticket ticket = ticketPool.poll();
                    if (ticket != null){
                        ticket.setTicketStatus("Sold");
                        ticket.setCustomer(customer);
                        System.out.println("Ticket purchased  by Customer ID: " + customer.getCustomerid()  + " - Ticket " + ticket.getTicketid());
                    }

                    lock.notifyAll();

                    try{
                        Thread.sleep(1000 / configuration.getTicketReleaseRate());
                    }catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
}












