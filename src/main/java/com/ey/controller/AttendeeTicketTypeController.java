package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.response.TicketTypeResponse;
import com.ey.service.TicketTypeService;

@RestController
@RequestMapping("/api/attendee/events/{eventId}/ticket-types")
//@PreAuthorize("hasRole('ATTENDEE')")
public class AttendeeTicketTypeController {

	@Autowired
    private TicketTypeService ticketTypeService;

    @GetMapping
    public ResponseEntity<List<TicketTypeResponse>> list(@PathVariable Long eventId) {
        return ticketTypeService.getTicketTypes(eventId);
    }
}
