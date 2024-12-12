package com.backend.realtimeticketingsystem_backend.Repository;

import com.backend.realtimeticketingsystem_backend.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
