package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.EventRequest;
import com.ey.dto.request.UpdateEventRequest;
import com.ey.dto.response.EventAttendeeResponse;
import com.ey.dto.response.EventResponse;
import com.ey.dto.response.EventStaffResponse;
import com.ey.entity.Event;
import com.ey.entity.StaffEventAssignment;
import com.ey.entity.User;
import com.ey.enums.EventStatus;
import com.ey.exception.EventNotFoundException;
import com.ey.exception.UserNotFoundException;
import com.ey.maper.EventMapper;
import com.ey.repository.EventRepository;
import com.ey.repository.StaffEventAssignmentRepository;
import com.ey.repository.TicketRepository;
import com.ey.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {

	@Autowired
    private EventRepository eventRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
    private StaffEventAssignmentRepository assignmentRepository;
	
	private Logger logger=LoggerFactory.getLogger(EventService.class);

    /* ORGANIZER */
    public ResponseEntity<EventResponse> createEvent(EventRequest request, String email) {

        User organizer = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                	logger.error("User not found");
                	return new UserNotFoundException("User not found");});
        
        Event event=EventMapper.toEntity(request, organizer);

        event.setOrganizer(organizer);
        event.setStatus(EventStatus.DRAFT);

        
        Event saved =eventRepository.save(event);
        
        logger.info("Event created successfully");
        return ResponseEntity.ok(EventMapper.toResponse(saved));
    }

    public ResponseEntity<List<EventResponse>> getMyEvents(String email) {

        User organizer = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                	logger.error("User not found");
                	return new UserNotFoundException("User not found");});

        List<Event> events =eventRepository.findByOrganizerId(organizer.getId());
        
        List<EventResponse>responseEvents=events.stream().map(EventMapper::toResponse).toList();
        logger.info("All Events are shown");
        return ResponseEntity.ok(responseEvents);
    }

    public ResponseEntity<?> publishEvent(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                	logger.error("Event not found");
                	return new EventNotFoundException("Event not found");});

        if(event.getStatus() != EventStatus.DRAFT) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Only draft events can be published");
        }
        
        event.setStatus(EventStatus.PUBLISHED);
        
        Event saved=eventRepository.save(event);
        
        EventResponse response=EventMapper.toResponse(saved);
        logger.info("Event published successfully");
        return ResponseEntity.ok(response);
    }


	public ResponseEntity<?> cancelEvent(Long eventId) {
		Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                	logger.error("Event not found");
                	return new EventNotFoundException("Event not found");});

		if(event.getStatus() != EventStatus.DRAFT) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body("Only draft events can be published");
		}
		
        event.setStatus(EventStatus.CANCELLED);
        
        Event saved=eventRepository.save(event);
        
        EventResponse response=EventMapper.toResponse(saved);
        
        logger.info("Event cancelled");
        return ResponseEntity.ok(response);
	}
    
	public ResponseEntity<EventResponse> updateEvent(UpdateEventRequest request, String email) {
		User organizer = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                	logger.error("User not found");
                	return new UserNotFoundException("User not found");});
		
		Event event = eventRepository.findById(request.getId())
                .orElseThrow(() -> {
                	logger.error("Event not found");
                	return new EventNotFoundException("Event not found");});
		
		EventMapper.updateEntity(request, event, organizer);
		Event updated=eventRepository.save(event);
		EventResponse response=EventMapper.toResponse(updated);
		
		logger.info("Event updated successfully");
		return ResponseEntity.ok(response);
	}
	
	@Transactional
	public ResponseEntity<String> deleteEvent(Long eventId, String organizerEmail) {
	    Event event = eventRepository.findById(eventId)
	            .orElseThrow(() -> new EventNotFoundException("Event not found"));

	    if (!event.getOrganizer().getEmail().equals(organizerEmail)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                .body("You are not allowed to delete this event");
	    }

	    eventRepository.delete(event);
	    return ResponseEntity.ok("Event deleted successfully");
	}

	
    public ResponseEntity<String> assignStaff(Long eventId,Long staffId,String organizerEmail) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                	logger.error("Event not found");
                	return new EventNotFoundException("Event not found");});

        if (!event.getOrganizer().getEmail().equals(organizerEmail)) {
        	logger.error("This Organizer is not allowed");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> {
                	logger.error("Staff not found");
                	return new UserNotFoundException("Staff not found");});
        
        if(assignmentRepository.existsByEventIdAndStaffId(eventId, staffId)) {
            return ResponseEntity.badRequest().body("Staff already assigned");
        }

        StaffEventAssignment assign=new StaffEventAssignment();
        assign.setEvent(event);
        assign.setStaff(staff);
        
        assignmentRepository.save(assign);
        
        logger.info("Staff assigned successfully");
        return ResponseEntity.ok("Staff assigned successfully");
    }
    
    @Transactional
    public ResponseEntity<String> removeStaff(Long eventId, Long staffId, String organizerEmail) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        if (!event.getOrganizer().getEmail().equals(organizerEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not allowed");
        }

        StaffEventAssignment assignment = assignmentRepository
                .findByEventIdAndStaffId(eventId, staffId)
                .orElseThrow(() -> new RuntimeException("Staff not assigned to this event"));

        assignmentRepository.delete(assignment);
        return ResponseEntity.ok("Staff removed successfully");
    }

    
    public ResponseEntity<List<EventStaffResponse>> getEventStaff(Long eventId,String organizerEmail) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                	logger.error("Event not found");
                	return new RuntimeException("Event not found");});

        if (!event.getOrganizer().getEmail().equals(organizerEmail)) {
        	logger.error("This Organizer is not allowed");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<EventStaffResponse> response =
                assignmentRepository.findByEventId(eventId)
                        .stream()
                        .map(a -> new EventStaffResponse(
                                a.getStaff().getId(),
                                a.getStaff().getName(),
                                a.getStaff().getEmail()
                        ))
                        .toList();

        logger.info("Showing All staffs of an event");
        return ResponseEntity.ok(response);
    }
    
    /* ATTENDEE */
    public ResponseEntity<List<EventResponse>> getPublishedEvents() {
    	
    	List<Event>events=eventRepository.findByStatus(EventStatus.PUBLISHED);
    	
    	List<EventResponse>responseEvents=events.stream().map(EventMapper::toResponse).toList();
        
    	logger.info("Event Published Successfully");
    	return ResponseEntity.ok(responseEvents);
    }

    public ResponseEntity<EventResponse> getEvent(Long id) {
    	Event event=eventRepository.findById(id)
                .orElseThrow(() -> {
                	logger.error("Event not found");
                	return new RuntimeException("Event not found");});
    	
    	EventResponse response=EventMapper.toResponse(event);
    	
    	logger.info("Get Event by ID");
        return ResponseEntity.ok(response);
    }

    /* STAFF */
    public ResponseEntity<List<EventAttendeeResponse>> getAttendees(Long eventId,String staffEmail) {

        User staff = userRepository.findByEmail(staffEmail)
                .orElseThrow(() -> {
                	logger.error("Staff not found");
                	return new RuntimeException("Staff not found");});

        boolean assigned = assignmentRepository
                .existsByStaffIdAndEventId(staff.getId(), eventId);

        if (!assigned) {
        	logger.error("The Staff is not allowed to access here");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<EventAttendeeResponse> response =
                ticketRepository.findAllByEventId(eventId)
                        .stream()
                        .map(t->new EventAttendeeResponse(
                        		t.getAttendee().getId(),
                        		t.getAttendee().getName(),
                        		t.getAttendee().getEmail(),
                        		t.getTicketType().getName(),
                        		t.getStatus(),
                        		t.getTicketQr().getStatus()
                        ))
                        .toList();

        logger.info("Getting all attendees of an event");
        return ResponseEntity.ok(response);
    }

	
}

