package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.TicketTypeRequest;
import com.ey.dto.response.TicketTypeResponse;
import com.ey.entity.Event;
import com.ey.entity.TicketType;
import com.ey.entity.User;
import com.ey.maper.TicketTypeMapper;
import com.ey.repository.EventRepository;
import com.ey.repository.TicketTypeRepository;
import com.ey.repository.UserRepository;

@Service
public class TicketTypeService {

	@Autowired
    private TicketTypeRepository ticketTypeRepository;
	
	@Autowired
    private EventRepository eventRepository;
	
	@Autowired
    private UserRepository userRepository;

    /* ORGANIZER */
    public ResponseEntity<TicketTypeResponse> createTicketType(Long eventId,TicketTypeRequest request,String email) {

        User organizer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        if (!event.getOrganizer().getId().equals(organizer.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        TicketType ticketType=TicketTypeMapper.toEntity(request, event);

        ticketType.setEvent(event);
        ticketType.setSold(0);

        TicketType saved=ticketTypeRepository.save(ticketType);
        
        TicketTypeResponse response=TicketTypeMapper.toResponse(saved);
        return ResponseEntity.ok(response);
    }

    /* ATTENDEE */
    public ResponseEntity<List<TicketTypeResponse>> getTicketTypes(Long eventId) {
        
    	List<TicketType>ticketTypes=ticketTypeRepository.findByEventId(eventId);
    	
    	List<TicketTypeResponse>ticketTypeResponses=ticketTypes.stream()
    												.map(TicketTypeMapper::toResponse)
    												.toList();
    	
    	return ResponseEntity.ok(ticketTypeResponses);
    }
}

