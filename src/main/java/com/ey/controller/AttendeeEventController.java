package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.response.EventResponse;
import com.ey.service.EventService;

@RestController
@RequestMapping("/api/attendee/events")
//@PreAuthorize("hasRole('ATTENDEE')")
public class AttendeeEventController {

	@Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventResponse>> browseEvents() {
        return eventService.getPublishedEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }
}

