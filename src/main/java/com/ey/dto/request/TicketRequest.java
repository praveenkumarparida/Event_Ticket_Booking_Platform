package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;

public class TicketRequest {

	private String status;

    @NotNull(message = "ticketTypeId is required")
    private Long ticketTypeId;

    @NotNull(message = "attendeeId is required")
    private Long attendeeId;

	public TicketRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketRequest(String status, @NotNull Long ticketTypeId, @NotNull Long attendeeId) {
		super();
		this.status = status;
		this.ticketTypeId = ticketTypeId;
		this.attendeeId = attendeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(Long ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public Long getAttendeeId() {
		return attendeeId;
	}

	public void setAttendeeId(Long attendeeId) {
		this.attendeeId = attendeeId;
	}

    
}
