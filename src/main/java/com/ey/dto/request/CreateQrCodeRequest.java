package com.ey.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class CreateQrCodeRequest {

	@NotNull(message = "ticketId is required")
    private UUID ticketId;
	
	public CreateQrCodeRequest(@NotNull(message = "ticketId is required") UUID ticketId) {
		super();
		this.ticketId = ticketId;
	}

	public CreateQrCodeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UUID getTicketId() {
		return ticketId;
	}

	public void setTicketId(UUID ticketId) {
		this.ticketId = ticketId;
	}
	
	

}
