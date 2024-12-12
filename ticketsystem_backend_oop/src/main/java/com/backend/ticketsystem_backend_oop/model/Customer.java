package com.backend.ticketsystem_backend_oop.model;


public class Customer {
    private Long Customerid;

    public Customer(Long customerid) {
        this.Customerid = customerid;

    }

    public Long getCustomerid() {
        return Customerid;
    }

    public void setCustomerid(Long customerid) {
        Customerid = customerid;
    }


}
