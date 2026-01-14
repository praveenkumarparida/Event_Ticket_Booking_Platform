package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateTicketTypeRequest {
	
	@NotBlank(message = "Ticket type name is required")
    private String name;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    private Double price;

    private String description;
    
    @NotNull(message = "Total available is required")
    @PositiveOrZero(message = "Total available must be zero or greater")
    private Integer totalAvailable;

    
	public CreateTicketTypeRequest(@NotBlank(message = "Ticket type name is required") String name,
			@NotNull(message = "Price is required") @PositiveOrZero(message = "Price must be zero or greater") Double price,
			String description,
			@NotNull(message = "Total available is required") @PositiveOrZero(message = "Total available must be zero or greater") Integer totalAvailable) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.totalAvailable = totalAvailable;
	}

	public CreateTicketTypeRequest() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTotalAvailable() {
		return totalAvailable;
	}

	public void setTotalAvailable(Integer totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
    
    


}
