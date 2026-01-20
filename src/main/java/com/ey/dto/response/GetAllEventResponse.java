package com.ey.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.ey.enums.EventStatus;

public class GetAllEventResponse {

	private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private String venue;
    private EventStatus status;
    
    private List<TicketTypeResponse>ticketTypes=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus eventStatus) {
		this.status = eventStatus;
	}

	public List<TicketTypeResponse> getTicketTypes() {
		return ticketTypes;
	}

	public void setTicketTypes(List<TicketTypeResponse> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}
    
    
}
