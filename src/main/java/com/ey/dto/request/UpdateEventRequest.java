package com.ey.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateEventRequest {

	@NotNull(message = "ID is required")
	private Long id;
	
	@NotBlank(message = "Name is required")
    private String name;
	
	@NotBlank(message = "StartTime is required")
    private LocalDateTime startTime;
	
	@NotBlank(message = "EndTime is required")
    private LocalDateTime endTime;
	
	@NotBlank(message = "Venue is required")
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
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
}
