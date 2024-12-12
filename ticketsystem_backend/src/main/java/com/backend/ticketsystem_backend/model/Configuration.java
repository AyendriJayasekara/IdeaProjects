package com.backend.ticketsystem_backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Configuration {

    @Id
    private Long id = 1L; // Single configuration entry
    private int TotalTicketsCount;
    private int TicketReleaseRate;
    private int TicketPurchaseRate;
    private int MaxTicketCapacity;
    private int MaxTicketsPerCustomer;

    // Getters and Setters
    public int getTotalTicketsCount() {
        return TotalTicketsCount;
    }

    public void setTotalTicketsCount(int TotalTicketsCount) {
        this.TotalTicketsCount= TotalTicketsCount;
    }

    public int getTicketReleaseRate() {
        return TicketReleaseRate;
    }

    public void setTicketReleaseRate(int TicketReleaseRate) {
        this. TicketReleaseRate= TicketReleaseRate;
    }

    public int getTicketPurchaseRate() {
        return TicketPurchaseRate;
    }

    public void setTicketPurchaseRate(int TicketPurchaseRate) {
        this.TicketPurchaseRate = TicketPurchaseRate;
    }

    public int getMaxTicketCapacity() {
        return MaxTicketCapacity;
    }

    public void setMaxTicketCapacity(int MaxTicketCapacity) {
        this.MaxTicketCapacity = MaxTicketCapacity;
    }

    public int getMaxTicketsPerCustomer() {
        return MaxTicketsPerCustomer;
    }

    public void setMaxTicketsPerCustomer(int MaxTicketsPerCustomer) {
        this.MaxTicketsPerCustomer = MaxTicketsPerCustomer;
    }
}

