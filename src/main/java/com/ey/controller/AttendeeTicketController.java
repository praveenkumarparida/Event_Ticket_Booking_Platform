package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.response.TicketResponse;
import com.ey.service.TicketPurchaseService;

@RestController
@RequestMapping("/api/attendee/tickets")
//@PreAuthorize("hasRole('ATTENDEE')")
public class AttendeeTicketController {

	@Autowired
    private TicketPurchaseService ticketPurchaseService;

    @PostMapping("/purchase/{ticketTypeId}")
    public ResponseEntity<?> purchase(@PathVariable Long ticketTypeId,Authentication authentication) {

        return ticketPurchaseService.purchaseTicket(ticketTypeId,authentication.getName());
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> myTickets(Authentication authentication) {

        return ticketPurchaseService.myTickets(authentication.getName());
    }
}

