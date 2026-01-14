package com.ey.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ey.enums.EventStatusEnum;

public class CreateEventResponse {

	private UUID id;

    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;

    private UUID organizerId;

    private List<CreateTicketTypeResponse> ticketTypes = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public UUID getOrganizerId() {
		return organizerId;
	}
	public void setOrganizerId(UUID organizerId) {
		this.organizerId = organizerId;
	}
	public List<CreateTicketTypeResponse> getTicketTypes() {
		return ticketTypes;
	}
	public void setTicketTypes(List<CreateTicketTypeResponse> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    

}
