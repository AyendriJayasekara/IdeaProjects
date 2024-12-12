package com.backend.ticketsystem_backend_oop.model;

public class Ticket {
    private Long Ticketid;
    private String TicketStatus;
    private Vendor vendor;
    private Customer customer;

    public Ticket(Long ticketid, String TicketStatus, Vendor vendor) {
        this.Ticketid = ticketid;
        this.TicketStatus = TicketStatus;
        this.vendor = vendor;

    }

    public Long getTicketid() {
        return Ticketid;
    }

    public void setTicketid(Long ticketid) {
        Ticketid = ticketid;
    }

    public String getTicketStatus() {
        return TicketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        TicketStatus = ticketStatus;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
