package com.backend.ticketsystem_backend_oop.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Configuration {
    private int TotalTicketsCount;
    private int MaxTicketCapacity;
    private int TicketReleaseRate;
    private int TicketPurchaseRate;
    private int vendorcount;
    private int customercount;


    public Configuration(
            @Value("${ticket.totalCount:0}") int TotalTicketsCount,
            @Value("${ticket.maxCapacity}") int MaxTicketCapacity,
            @Value("${ticket.releaseRate}") int TicketReleaseRate,
            @Value("${ticket.purchaseRate}") int TicketPurchaseRate,
            @Value("${ticket.quantity}") int Quantity,
            @Value("${ticket.vendorcount}") int vendorcount,
            @Value("${ticket.customercount}") int customercount) {

        this.TotalTicketsCount = TotalTicketsCount;
        this.MaxTicketCapacity = MaxTicketCapacity;
        this.TicketReleaseRate = TicketReleaseRate;
        this.TicketPurchaseRate = TicketPurchaseRate;
        this.vendorcount = vendorcount;
        this.customercount = customercount;
    }

    public int getTotalTicketsCount() {
        return TotalTicketsCount;
    }

    public void setTotalTicketsCount(int totalTicketsCount) {
        TotalTicketsCount = totalTicketsCount;
    }


    public int getMaxTicketCapacity() {
        return MaxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        MaxTicketCapacity = maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return TicketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        TicketReleaseRate = ticketReleaseRate;
    }

    public int getTicketPurchaseRate() {
        return TicketPurchaseRate;
    }

    public void setTicketPurchaseRate(int ticketPurchaseRate) {
        TicketPurchaseRate = ticketPurchaseRate;
    }

    public int getVendorcount() {
        return vendorcount;
    }

    public void setVendorcount(int vendorcount) {
        this.vendorcount = vendorcount;
    }

    public int getCustomercount() {
        return customercount;
    }

    public void setCustomercount(int customercount) {
        this.customercount = customercount;
    }
}
