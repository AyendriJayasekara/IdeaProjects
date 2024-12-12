package com.backend.realtimeticketingsystem_backend.Repository;

import com.backend.realtimeticketingsystem_backend.model.Customer;
import com.backend.realtimeticketingsystem_backend.model.Ticket;
import com.backend.realtimeticketingsystem_backend.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
     List<Ticket> findByStatus(String status);
     Ticket findFirstByStatus(String status);

    List<Ticket> findByVendor(Vendor vendor);
    List<Ticket> findByCustomer(Customer customer);

}
