package com.ey.mapper;

import com.ey.dto.request.CreateTicketRequest;
import com.ey.dto.response.CreateTicketResponse;
import com.ey.entity.Event;
import com.ey.entity.Ticket;
import com.ey.entity.TicketType;
import com.ey.entity.User;
import com.ey.enums.TicketStatusEnum;

public class TicketMapper {

	public static Ticket toTicket(CreateTicketRequest request, TicketType ticketType, User purchaser) {
        Ticket ticket = new Ticket();
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(purchaser);
        ticket.setStatus(request.getStatus() != null ? request.getStatus() : TicketStatusEnum.PURCHASED);
        return ticket;
    }


	public static CreateTicketResponse toCreateTicketResponse(Ticket ticket) {
        CreateTicketResponse response = new CreateTicketResponse();
        response.setId(ticket.getId());
        response.setStatus(ticket.getStatus());
        response.setPurchaserId(ticket.getPurchaser() != null ? ticket.getPurchaser().getId() : null);
        response.setTicketTypeId(ticket.getTicketType().getId());
        response.setTicketTypeName(ticket.getTicketType().getName());
        response.setTicketTypePrice(ticket.getTicketType().getPrice());

        Event event = ticket.getTicketType().getEvent();
        if (event != null) {
            response.setEventId(event.getId());
            response.setEventName(event.getName());
            response.setEventVenue(event.getVenue());
            response.setEventStart(event.getStart());
            response.setEventEnd(event.getEnd());
        }

        response.setCreatedAt(ticket.getCreatedAt());
        response.setUpdatedAt(ticket.getUpdatedAt());
        return response;
    }

}
