package com.ey.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ey.enums.TicketStatusEnum;

public class CreateTicketResponse {

	private UUID id;
    private TicketStatusEnum status;

    private UUID purchaserId;

    private UUID ticketTypeId;
    private String ticketTypeName;
    private Double ticketTypePrice;

    private UUID eventId;
    private String eventName;
    private String eventVenue;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public CreateTicketResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateTicketResponse(UUID id, TicketStatusEnum status, UUID purchaserId, UUID ticketTypeId,
			String ticketTypeName, Double ticketTypePrice, UUID eventId, String eventName, String eventVenue,
			LocalDateTime eventStart, LocalDateTime eventEnd, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.status = status;
		this.purchaserId = purchaserId;
		this.ticketTypeId = ticketTypeId;
		this.ticketTypeName = ticketTypeName;
		this.ticketTypePrice = ticketTypePrice;
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventVenue = eventVenue;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public TicketStatusEnum getStatus() {
		return status;
	}
	public void setStatus(TicketStatusEnum status) {
		this.status = status;
	}
	public UUID getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(UUID purchaserId) {
		this.purchaserId = purchaserId;
	}
	public UUID getTicketTypeId() {
		return ticketTypeId;
	}
	public void setTicketTypeId(UUID ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}
	public String getTicketTypeName() {
		return ticketTypeName;
	}
	public void setTicketTypeName(String ticketTypeName) {
		this.ticketTypeName = ticketTypeName;
	}
	public Double getTicketTypePrice() {
		return ticketTypePrice;
	}
	public void setTicketTypePrice(Double ticketTypePrice) {
		this.ticketTypePrice = ticketTypePrice;
	}
	public UUID getEventId() {
		return eventId;
	}
	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventVenue() {
		return eventVenue;
	}
	public void setEventVenue(String eventVenue) {
		this.eventVenue = eventVenue;
	}
	public LocalDateTime getEventStart() {
		return eventStart;
	}
	public void setEventStart(LocalDateTime eventStart) {
		this.eventStart = eventStart;
	}
	public LocalDateTime getEventEnd() {
		return eventEnd;
	}
	public void setEventEnd(LocalDateTime eventEnd) {
		this.eventEnd = eventEnd;
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
