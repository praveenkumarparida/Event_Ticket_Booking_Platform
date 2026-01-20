package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.EventRequest;
import com.ey.dto.request.UpdateEventRequest;
import com.ey.dto.response.EventResponse;
import com.ey.dto.response.EventStaffResponse;
import com.ey.service.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/organizer/events")
//@PreAuthorize("hasRole('ORGANIZER')")
public class OrganizerEventController {

	@Autowired
    private EventService eventService;
	
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest request, Authentication authentication) {
    	System.out.println(request);
        return eventService.createEvent(request,authentication.getName());
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> myEvents(Authentication authentication) {

        return eventService.getMyEvents(authentication.getName());
    }
    
    @PutMapping
    public ResponseEntity<EventResponse> updateEvent(@Valid @RequestBody UpdateEventRequest request,Authentication authentication){
    	return eventService.updateEvent(request,authentication.getName());
    }

    @PutMapping("/{eventId}/publish")
    public ResponseEntity<?> publishEvent(@PathVariable Long eventId) {

        return eventService.publishEvent(eventId);
    }
    
    @PutMapping("/{eventId}/cancel")
    public ResponseEntity<?> cancelEvent(@PathVariable Long eventId) {
    	
    	return eventService.cancelEvent(eventId);
    }
    
    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(
            @PathVariable Long eventId,
            Authentication authentication) {

        return eventService.deleteEvent(eventId, authentication.getName());
    }

    
    @PostMapping("/{eventId}/assign-staff/{staffId}")
    public ResponseEntity<String> assignStaff(@PathVariable Long eventId,@PathVariable Long staffId,Authentication authentication) {

        return eventService.assignStaff(eventId,staffId,authentication.getName());
    }
    
    @DeleteMapping("/{eventId}/assign-staff/{staffId}")
    public ResponseEntity<String> removeStaff(
            @PathVariable Long eventId,
            @PathVariable Long staffId,
            Authentication authentication) {

        return eventService.removeStaff(eventId, staffId, authentication.getName());
    }

    
    @GetMapping("/{eventId}/staffs")
    public ResponseEntity<List<EventStaffResponse>> getStaffs(@PathVariable Long eventId,Authentication authentication) {

        return eventService.getEventStaff(eventId,authentication.getName()
        );
    }
}

