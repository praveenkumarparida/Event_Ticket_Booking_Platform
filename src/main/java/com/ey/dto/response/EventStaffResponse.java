package com.ey.dto.response;

public class EventStaffResponse {
	private Long staffId;
    private String name;
    private String email;
    
    
	public EventStaffResponse(Long staffId, String name, String email) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.email = email;
	}
	public EventStaffResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
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
    
    
}
