package com.backend.ticketsystem_backend_oop.model;

public class Vendor {

    private Long Vendorid;


    public Vendor(Long vendorid ) {
        Vendorid = vendorid;

    }

    public Long getVendorid() {
        return Vendorid;
    }

    public void setVendorid(Long vendorid) {
        Vendorid = vendorid;
    }

}
