package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.CreateEventRequest;
import com.ey.service.EventService;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@PostMapping
	public ResponseEntity<CreateEventRequest> createEvent(@RequestBody CreateEventRequest request){
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
