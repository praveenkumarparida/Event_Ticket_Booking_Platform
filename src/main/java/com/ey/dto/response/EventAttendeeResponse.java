package com.ey.dto.response;

import com.ey.enums.QrStatus;
import com.ey.enums.TicketStatus;

public class EventAttendeeResponse {
	
	private Long attendeeId;
    private String name;
    private String email;
    private String ticketType;
    private TicketStatus ticketStatus;
    private QrStatus qrStatus;
	public EventAttendeeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventAttendeeResponse(Long attendeeId, String name, String email, String ticketType,
			TicketStatus ticketStatus, QrStatus qrStatus) {
		super();
		this.attendeeId = attendeeId;
		this.name = name;
		this.email = email;
		this.ticketType = ticketType;
		this.ticketStatus = ticketStatus;
		this.qrStatus = qrStatus;
	}
	public Long getAttendeeId() {
		return attendeeId;
	}
	public void setAttendeeId(Long attendeeId) {
		this.attendeeId = attendeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public QrStatus getQrStatus() {
		return qrStatus;
	}
	public void setQrStatus(QrStatus qrStatus) {
		this.qrStatus = qrStatus;
	}
    
    
}
