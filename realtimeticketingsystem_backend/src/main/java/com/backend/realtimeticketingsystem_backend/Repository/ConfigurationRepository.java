package com.backend.realtimeticketingsystem_backend.Repository;

import com.backend.realtimeticketingsystem_backend.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {





}

