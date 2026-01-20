package com.ey.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TicketTypeRequest {

	@NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    @Positive
    private Double price;

    @NotNull(message = "totalAvailable is required")
    @Min(0)
    private Integer totalAvailable;

    @Min(0)
    private Integer sold;
    
//    @NotNull
    private Long eventId;

	public TicketTypeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketTypeRequest(@NotBlank String name, @NotNull @Positive Double price,
			@NotNull @Min(0) Integer totalAvailable, @Min(0) Integer sold, @NotNull Long eventId) {
		super();
		this.name = name;
		this.price = price;
		this.totalAvailable = totalAvailable;
		this.sold = sold;
		this.eventId = eventId;
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

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}


}
