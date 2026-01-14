package com.ey.mapper;

import java.util.stream.Collectors;

import com.ey.dto.request.CreateEventRequest;
import com.ey.dto.response.CreateEventResponse;
import com.ey.entity.Event;

public class EventMapper {

	public static Event toEvent(CreateEventRequest request) {
        Event event = new Event();
        event.setName(request.getName());
        event.setStart(request.getStart());
        event.setEnd(request.getEnd());
        event.setVenue(request.getVenue());
        event.setSalesStart(request.getSalesStart());
        event.setSalesEnd(request.getSalesEnd());
        event.setStatus(request.getStatus());
        return event;
    }
	

	public static CreateEventResponse toCreateEventResponse(Event event) {
        CreateEventResponse response = new CreateEventResponse();
        response.setId(event.getId());
        response.setName(event.getName());
        response.setStart(event.getStart());
        response.setEnd(event.getEnd());
        response.setVenue(event.getVenue());
        response.setSalesStart(event.getSalesStart());
        response.setSalesEnd(event.getSalesEnd());
        response.setStatus(event.getStatus());
        response.setOrganizerId(event.getOrganizer() != null ? event.getOrganizer().getId() : null);
        response.setCreatedAt(event.getCreatedAt());
        response.setUpdatedAt(event.getUpdatedAt());
        if (event.getTicketTypes() != null) {
            response.setTicketTypes(event.getTicketTypes().stream()
                    .map(TicketTypeMapper::toCreateTicketTypeResponse)
                    .collect(Collectors.toList()));
        }
        return response;
    }


}
