package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.TicketTypeRequest;
import com.ey.dto.response.TicketTypeResponse;
import com.ey.service.TicketTypeService;

@RestController
@RequestMapping("/api/organizer/events/{eventId}/ticket-types")
//@PreAuthorize("hasRole('ORGANIZER')")
public class OrganizerTicketTypeController {

	@Autowired
    private TicketTypeService ticketTypeService;

    @PostMapping
    public ResponseEntity<TicketTypeResponse> create(@PathVariable Long eventId,@RequestBody TicketTypeRequest ticketType,Authentication authentication) {

        return ticketTypeService.createTicketType(eventId,ticketType,authentication.getName());
    }
}

