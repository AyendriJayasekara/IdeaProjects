package com.backend.ticketsystem_backend_oop.service;

import com.backend.ticketsystem_backend_oop.model.Configuration;
import com.backend.ticketsystem_backend_oop.model.Ticket;
import com.backend.ticketsystem_backend_oop.model.Vendor;

import java.util.List;
import java.util.Queue;

public class VendorService implements Runnable {

    private final Configuration configuration;
    private final List<Vendor> vendors;
    private final Object lock;
    private final Queue<Ticket> ticketPool;
    private static Long ticketid = 1L;


    public VendorService(Configuration configuration,List<Vendor> vendors,  Object lock,Queue<Ticket> ticketPool ) {
        this.configuration = configuration;
        this.vendors = vendors;
        this.lock = lock;
        this.ticketPool = ticketPool;
    }


    @Override
    public void run(){
        vendors.forEach(vendor -> {
            synchronized (lock){

                for(int i =0 ; i < configuration.getTotalTicketsCount(); i++ ){
                    if (ticketPool.size() >= configuration.getMaxTicketCapacity()){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                }

                Ticket ticket = new Ticket(ticketid++,"Unsold", vendor);
                ticketPool.offer(ticket); // ticket add to pool
                System.out.println("Ticket released by Vendor ID: " + vendor.getVendorid()  + " - Ticket " + ticket.getTicketid());
                }

                lock.notifyAll();

                try{
                    Thread.sleep(1000 / configuration.getTicketReleaseRate());
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

        });
    }
}










