package com.ey.dto.response;

public class TicketResponse {

	private Long id;
    private String status;

    private Long ticketTypeId;
    private String ticketTypeName;
    
    private Long eventId; 
    private String eventName;

    private Long attendeeId;
    private String attendeeName;
	public TicketResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TicketResponse(Long id, String status, Long ticketTypeId, String ticketTypeName, Long eventId,
			String eventName, Long attendeeId, String attendeeName) {
		super();
		this.id = id;
		this.status = status;
		this.ticketTypeId = ticketTypeId;
		this.ticketTypeName = ticketTypeName;
		this.eventId = eventId;
		this.eventName = eventName;
		this.attendeeId = attendeeId;
		this.attendeeName = attendeeName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getTicketTypeName() {
		return ticketTypeName;
	}
	public void setTicketTypeName(String ticketTypeName) {
		this.ticketTypeName = ticketTypeName;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Long getAttendeeId() {
		return attendeeId;
	}
	public void setAttendeeId(Long attendeeId) {
		this.attendeeId = attendeeId;
	}
	public String getAttendeeName() {
		return attendeeName;
	}
	public void setAttendeeName(String attendeeName) {
		this.attendeeName = attendeeName;
	}
    
    

}
