package com.ey.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket_types")
public class TicketType {

	   @Id
	   @Column(name = "id", nullable = false, updatable = false)
	   @GeneratedValue(strategy = GenerationType.UUID)
	   private UUID id;
	    
	   @Column(name = "name", nullable = false)
	   private String name;
	   
	   @Column(name = "price", nullable = false)
	   private Double price;
	   
	   @Column(name = "total_available")
	   private Integer totalAvailable;
	   
	   @ManyToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name = "event_id")
	   private Event event;
	   
	   @OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL)
	   private List<Ticket> tickets = new ArrayList<>();
	   
	   @CreatedDate
	   @Column(name = "created_at", updatable = false, nullable = false)
	   private LocalDateTime createdAt;
	   
	   @LastModifiedDate
	   @Column(name = "updated_at", nullable = false)
	   private LocalDateTime updatedAt;

	   @Override
	   public int hashCode() {
		return Objects.hash(createdAt, id, name, price, totalAvailable, updatedAt);
	   }

	   @Override
	   public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketType other = (TicketType) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(totalAvailable, other.totalAvailable) && Objects.equals(updatedAt, other.updatedAt);
	   }

	   public UUID getId() {
		   return id;
	   }

	   public void setId(UUID id) {
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

	   public Event getEvent() {
		   return event;
	   }

	   public void setEvent(Event event) {
		   this.event = event;
	   }

	   public List<Ticket> getTickets() {
		   return tickets;
	   }

	   public void setTickets(List<Ticket> tickets) {
		   this.tickets = tickets;
	   }

	   public LocalDateTime getCreatedAt() {
		   return createdAt;
	   }

	   public void setCreatedAt(LocalDateTime createdAt) {
		   this.createdAt = createdAt;
	   }

	   public LocalDateTime getUpdatedAt() {
		   return updatedAt;
	   }

	   public void setUpdatedAt(LocalDateTime updatedAt) {
		   this.updatedAt = updatedAt;
	   }
	   
	   
}
