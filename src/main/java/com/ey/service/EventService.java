package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.EventRequest;
import com.ey.dto.request.UpdateEventRequest;
import com.ey.dto.response.EventResponse;
import com.ey.entity.Event;
import com.ey.entity.User;
import com.ey.enums.EventStatus;
import com.ey.maper.EventMapper;
import com.ey.repository.EventRepository;
import com.ey.repository.UserRepository;

@Service
public class EventService {

	@Autowired
    private EventRepository eventRepository;
	
	@Autowired
    private UserRepository userRepository;

    /* ORGANIZER */
    public ResponseEntity<EventResponse> createEvent(EventRequest request, String email) {

        User organizer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Event event=EventMapper.toEntity(request, organizer);

        event.setOrganizer(organizer);
        event.setStatus(EventStatus.DRAFT);

        
        Event saved =eventRepository.save(event);
        
        return ResponseEntity.ok(EventMapper.toResponse(saved));
    }

    public ResponseEntity<List<EventResponse>> getMyEvents(String email) {

        User organizer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Event> events =eventRepository.findByOrganizerId(organizer.getId());
        
        List<EventResponse>responseEvents=events.stream().map(EventMapper::toResponse).toList();
        return ResponseEntity.ok(responseEvents);
    }

    public ResponseEntity<EventResponse> publishEvent(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setStatus(EventStatus.PUBLISHED);
        
        Event saved=eventRepository.save(event);
        
        EventResponse response=EventMapper.toResponse(saved);
        return ResponseEntity.ok(response);
    }


	public ResponseEntity<EventResponse> cancelEvent(Long eventId) {
		Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setStatus(EventStatus.CANCELLED);
        
        Event saved=eventRepository.save(event);
        
        EventResponse response=EventMapper.toResponse(saved);
        return ResponseEntity.ok(response);
	}
    
	public ResponseEntity<EventResponse> updateEvent(UpdateEventRequest request, String email) {
		User organizer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		Event event = eventRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Event not found"));
		
		EventMapper.updateEntity(request, event, organizer);
		Event updated=eventRepository.save(event);
		EventResponse response=EventMapper.toResponse(updated);
		return ResponseEntity.ok(response);
	}
    
    /* ATTENDEE */
    public ResponseEntity<List<EventResponse>> getPublishedEvents() {
    	
    	List<Event>events=eventRepository.findByStatus(EventStatus.PUBLISHED);
    	
    	List<EventResponse>responseEvents=events.stream().map(EventMapper::toResponse).toList();
        return ResponseEntity.ok(responseEvents);
    }

    public ResponseEntity<EventResponse> getEvent(Long id) {
    	Event event=eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    	
    	EventResponse response=EventMapper.toResponse(event);
        return ResponseEntity.ok(response);
    }

	

}

