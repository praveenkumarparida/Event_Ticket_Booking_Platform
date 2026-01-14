package com.ey.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dto.request.CreateEventRequest;
import com.ey.entity.Event;
import com.ey.entity.TicketType;
import com.ey.entity.User;
import com.ey.exception.UserNotFoundException;
import com.ey.mapper.EventMapper;
import com.ey.mapper.TicketTypeMapper;
import com.ey.repository.EventRepository;
import com.ey.repository.UserRepository;

@Service
public class EventService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	public Event createEvent(UUID organizerId,CreateEventRequest event) {
		User organizer=userRepository.findById(organizerId).orElseThrow(()->new UserNotFoundException("User Id:"+organizerId+" not found"));
		
		List<TicketType>ticketTypeToCreate=event.getTicketTypes().stream()
			.map(TicketTypeMapper::toTicketType)
			.toList();
		
		Event eventToCreate=EventMapper.toEvent(event);
		eventToCreate.setOrganizer(organizer);
		eventToCreate.setTicketTypes(ticketTypeToCreate);
		return eventRepository.save(eventToCreate);
	}
	
	
}
