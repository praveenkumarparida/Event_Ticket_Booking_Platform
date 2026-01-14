package com.ey.dto.request;

import java.util.UUID;

import org.antlr.v4.runtime.misc.NotNull;

import com.ey.enums.TicketStatusEnum;

public class CreateTicketRequest {

    private UUID ticketTypeId;
    private TicketStatusEnum status;
	public CreateTicketRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateTicketRequest(UUID ticketTypeId, TicketStatusEnum status) {
		super();
		this.ticketTypeId = ticketTypeId;
		this.status = status;
	}
	public UUID getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(UUID ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	public TicketStatusEnum getStatus() {
		return status;
	}
	public void setStatus(TicketStatusEnum status) {
		this.status = status;
	}
    
    
}
