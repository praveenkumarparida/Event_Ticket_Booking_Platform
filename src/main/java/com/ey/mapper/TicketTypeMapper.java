package com.ey.mapper;

import com.ey.dto.request.CreateTicketTypeRequest;
import com.ey.dto.response.CreateTicketTypeResponse;
import com.ey.entity.TicketType;

public class TicketTypeMapper {

	public static TicketType toTicketType(CreateTicketTypeRequest request) {
        TicketType ticketType = new TicketType();
        ticketType.setName(request.getName());
        ticketType.setPrice(request.getPrice());
        ticketType.setTotalAvailable(request.getTotalAvailable());
        return ticketType;
    }
	

	public static CreateTicketTypeResponse toCreateTicketTypeResponse(TicketType ticketType) {
        CreateTicketTypeResponse dto = new CreateTicketTypeResponse();
        dto.setId(ticketType.getId());
        dto.setName(ticketType.getName());
        dto.setPrice(ticketType.getPrice());
        dto.setTotalAvailable(ticketType.getTotalAvailable());
        return dto;
    }


}
