package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.response.AttendeeTicketResponse;
import com.ey.service.TicketService;

@RestController
@RequestMapping("/api/attendee/tickets")
//@PreAuthorize("hasRole('ATTENDEE')")
public class AttendeeTicketController {

	@Autowired
    private TicketService ticketService;

    @PostMapping("/purchase/{ticketTypeId}")
    public ResponseEntity<?> purchase(@PathVariable Long ticketTypeId,Authentication authentication) {

        return ticketService.purchaseTicket(ticketTypeId,authentication.getName());
    }

    @GetMapping
    public ResponseEntity<List<AttendeeTicketResponse>> myTickets(Authentication authentication) {

        return ticketService.myTickets(authentication.getName());
    }
    
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> cancel(@PathVariable Long ticketId,Authentication authentication) {

        return ticketService.cancelTicket(ticketId,authentication.getName());
    }
}

