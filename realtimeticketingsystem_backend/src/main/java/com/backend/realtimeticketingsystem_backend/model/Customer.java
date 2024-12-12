package com.backend.realtimeticketingsystem_backend.model;

import java.util.List;
public class Customer {


    private Long id;
    private String name;
    private List<Ticket> tickets;

    public Customer( String name){
        name=name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
