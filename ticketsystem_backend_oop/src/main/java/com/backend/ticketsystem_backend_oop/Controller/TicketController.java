package com.backend.ticketsystem_backend_oop.Controller;



import com.backend.ticketsystem_backend_oop.model.Configuration;
import com.backend.ticketsystem_backend_oop.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketservice")
@Service
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Endpoint to start the services (Vendor and Customer services)
    @PostMapping("/start")
    public String startServices() {
        ticketService.StartServices();
        return "Services started successfully.";
    }

    // Endpoint to stop the services
    @PostMapping("/stop")
    public String stopServices() {
        ticketService.StopServices();
        return "Services stopped successfully.";
    }

    // Endpoint to change the configuration (e.g., vendor count, customer count, total tickets)
    @PostMapping("/configure")
    public String configureServices(@RequestBody Configuration configuration) {
        ticketService.SetConfiguration(configuration);
        return "Configuration updated successfully.";
    }

}
