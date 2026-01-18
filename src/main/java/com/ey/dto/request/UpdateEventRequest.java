package com.ey.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class UpdateEventRequest {

	@NotBlank
	private Long id;
	@NotBlank
    private String name;
	@NotBlank
    private String startTime;
	@NotBlank
    private String endTime;
	@NotBlank
    private String venue;
	
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
	
}
