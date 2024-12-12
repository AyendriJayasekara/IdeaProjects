package com.example.OOP_.ticketing_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


   private String vendorName;
   private int customerCount;
   private int maxCapacity;
   private int releaseRate;
   private int retrievalRate;
   private String status;



   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getVendorName() {
      return vendorName;
   }

   public void setVendorName(String vendorName) {
      this.vendorName = vendorName;
   }

   public int getCustomerCount() {
      return customerCount;
   }

   public void setCustomerCount(int customerCount) {
      this.customerCount = customerCount;
   }

   public int getMaxCapacity() {
      return maxCapacity;
   }

   public void setMaxCapacity(int maxCapacity) {
      this.maxCapacity = maxCapacity;
   }

   public int getReleaseRate() {
      return releaseRate;
   }

   public void setReleaseRate(int releaseRate) {
      this.releaseRate = releaseRate;
   }

   public int getRetrievalRate() {
      return retrievalRate;
   }

   public void setRetrievalRate(int retrievalRate) {
      this.retrievalRate = retrievalRate;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }


}
