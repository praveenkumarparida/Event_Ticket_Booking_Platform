package com.ey.dto.response;

import java.time.LocalDateTime;

import com.ey.enums.TicketStatus;

public class AttendeeTicketResponse {

	private Long ticketId;
    private String eventName;
    private String ticketType;
    private Double price;
    private TicketStatus status;
    private LocalDateTime eventStartTime;
	public AttendeeTicketResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttendeeTicketResponse(Long ticketId, String eventName, String ticketType, Double price, TicketStatus status,
			LocalDateTime eventStartTime) {
		super();
		this.ticketId = ticketId;
		this.eventName = eventName;
		this.ticketType = ticketType;
		this.price = price;
		this.status = status;
		this.eventStartTime = eventStartTime;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public TicketStatus getStatus() {
		return status;
	}
	public void setStatus(TicketStatus status) {
		this.status = status;
	}
	public LocalDateTime getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(LocalDateTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
    
    
    
}
