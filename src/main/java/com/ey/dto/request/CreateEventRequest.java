package com.ey.dto.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ey.enums.EventStatusEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateEventRequest {

	@NotBlank(message = "Event name is required")
    private String name;

    private LocalDateTime start;
    private LocalDateTime end;

    @NotBlank(message = "Venue is required")
    private String venue;

    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;

    @NotNull(message = "Event status is required")
    private EventStatusEnum status = EventStatusEnum.DRAFT; // default

    @NotEmpty(message = "At least one ticket type is required")
    @Valid
    private List<CreateTicketTypeRequest> ticketTypes = new ArrayList<>();

    
    
	public CreateEventRequest(@NotBlank(message = "Event name is required") String name, LocalDateTime start,
			LocalDateTime end, @NotBlank(message = "Venue is required") String venue, LocalDateTime salesStart,
			LocalDateTime salesEnd, @NotNull(message = "Event status is required") EventStatusEnum status,
			@NotEmpty(message = "At least one ticket type is required") @Valid List<CreateTicketTypeRequest> ticketTypes) {
		super();
		this.name = name;
		this.start = start;
		this.end = end;
		this.venue = venue;
		this.salesStart = salesStart;
		this.salesEnd = salesEnd;
		this.status = status;
		this.ticketTypes = ticketTypes;
	}

	public CreateEventRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public LocalDateTime getSalesStart() {
		return salesStart;
	}

	public void setSalesStart(LocalDateTime salesStart) {
		this.salesStart = salesStart;
	}

	public LocalDateTime getSalesEnd() {
		return salesEnd;
	}

	public void setSalesEnd(LocalDateTime salesEnd) {
		this.salesEnd = salesEnd;
	}

	public EventStatusEnum getStatus() {
		return status;
	}

	public void setStatus(EventStatusEnum status) {
		this.status = status;
	}

	public List<CreateTicketTypeRequest> getTicketTypes() {
		return ticketTypes;
	}

	public void setTicketTypes(List<CreateTicketTypeRequest> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}

    
}
