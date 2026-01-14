package com.ey.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.ey.enums.EventStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "events") 
public class Event {
	   @Id
	   @Column(name = "id", nullable = false, updatable = false)
	   @GeneratedValue(strategy = GenerationType.UUID)
	   private UUID id;
	 
	   @Column(name = "name", nullable = false) 
	   private String name; 
	 
	   @Column(name = "start") 
	   private LocalDateTime start; 
	 
	   @Column(name = "end") 
	   private LocalDateTime end; 
	 
	   @Column(name = "venue", nullable = false) 
	   private String venue; 
	 
	   @Column(name = "sales_start") 
	   private LocalDateTime salesStart; 
	 
	   @Column(name = "sales_end") 
	   private LocalDateTime salesEnd; 
	 
	   @Column(name = "status", nullable = false) 
	   @Enumerated(EnumType.STRING) 
	   private EventStatusEnum status; 
	 
	   @ManyToOne(fetch = FetchType.LAZY) 
	   @JoinColumn(name = "organizer_id") 
	   private User organizer; 
	 
	   @ManyToMany(mappedBy = "attendingEvents") 
	   private List<User> attendees = new ArrayList<>();
	   
	   @ManyToMany(mappedBy = "staffingEvents") 
	   private List<User> staff = new ArrayList<>(); 
	   
	   @OneToMany(mappedBy = "event", cascade = CascadeType.ALL) 
	   private List<TicketType> ticketTypes = new ArrayList<>(); 
	 
	   @CreatedDate 
	   @Column(name = "created_at", updatable = false, nullable = false) 
	   private LocalDateTime createdAt; 
	 
	   @LastModifiedDate 
	   @Column(name = "updated_at", nullable = false) 
	   private LocalDateTime updatedAt;

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

	   public LocalDateTime getStart() {
		   return start;
	   }

	   public void setStart(LocalDateTime start) {
		   this.start = start;
	   }

	   public LocalDateTime getEnd() {
		   return end;
	   }

	   public void setEnd(LocalDateTime end) {
		   this.end = end;
	   }

	   public String getVenue() {
		   return venue;
	   }

	   public void setVenue(String venue) {
		   this.venue = venue;
	   }

	   public LocalDateTime getSalesStart() {
		   return salesStart;
	   }

	   public void setSalesStart(LocalDateTime salesStart) {
		   this.salesStart = salesStart;
	   }

	   public LocalDateTime getSalesEnd() {
		   return salesEnd;
	   }

	   public void setSalesEnd(LocalDateTime salesEnd) {
		   this.salesEnd = salesEnd;
	   }

	   public EventStatusEnum getStatus() {
		   return status;
	   }

	   public void setStatus(EventStatusEnum status) {
		   this.status = status;
	   }

	   public User getOrganizer() {
		   return organizer;
	   }

	   public void setOrganizer(User organizer) {
		   this.organizer = organizer;
	   }

	   public List<User> getAttendees() {
		   return attendees;
	   }

	   public void setAttendees(List<User> attendees) {
		   this.attendees = attendees;
	   }

	   public List<User> getStaff() {
		   return staff;
	   }

	   public void setStaff(List<User> staff) {
		   this.staff = staff;
	   }

	   public List<TicketType> getTicketTypes() {
		   return ticketTypes;
	   }

	   public void setTicketTypes(List<TicketType> ticketTypes) {
		   this.ticketTypes = ticketTypes;
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

	   @Override
	   public int hashCode() {
		return Objects.hash(createdAt, end, id, name, salesEnd, salesStart, start, status, updatedAt, venue);
	   }

	   @Override
	   public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(end, other.end)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(salesEnd, other.salesEnd) && Objects.equals(salesStart, other.salesStart)
				&& Objects.equals(start, other.start) && status == other.status
				&& Objects.equals(updatedAt, other.updatedAt) && Objects.equals(venue, other.venue);
	   } 
	   
	   
	   
}
