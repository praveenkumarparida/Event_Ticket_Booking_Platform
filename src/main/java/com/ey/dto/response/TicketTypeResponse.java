package com.ey.dto.response;

public class TicketTypeResponse {

	private Long id;
    private String name;
    private Double price;
    private Integer totalAvailable;
    private Integer sold;
    private Integer remaining; // derived: totalAvailable - sold

    private Long eventId;
    private String eventName;

	public TicketTypeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketTypeResponse(Long id, String name, Double price, Integer totalAvailable, Integer sold,
			Integer remaining, Long eventId, String eventName) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalAvailable = totalAvailable;
		this.sold = sold;
		this.remaining = remaining;
		this.eventId = eventId;
		this.eventName = eventName;
	}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getTotalAvailable() {
		return totalAvailable;
	}

	public void setTotalAvailable(Integer totalAvailable) {
		this.totalAvailable = totalAvailable;
	}

	public Integer getSold() {
		return sold;
	}

	public void setSold(Integer sold) {
		this.sold = sold;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
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
    
    

}
