package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.response.EventAttendeeResponse;
import com.ey.service.EventService;

@RestController
@RequestMapping("/api/staff/events")
public class StaffEventAttendeeController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/{eventId}/attendees")
    public ResponseEntity<List<EventAttendeeResponse>> getAttendees(@PathVariable Long eventId,Authentication authentication) {

        return eventService.getAttendees(eventId,authentication.getName());
    }
}
