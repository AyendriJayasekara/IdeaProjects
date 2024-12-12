package com.backend.realtimeticketingsystem_backend.Repository;

import com.backend.realtimeticketingsystem_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
