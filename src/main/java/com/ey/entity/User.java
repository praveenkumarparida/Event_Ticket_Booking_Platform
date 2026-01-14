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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class User {
	
	   @Id
	   @Column(name = "id", nullable = false, updatable = false)
	   @GeneratedValue(strategy = GenerationType.UUID)
	   private UUID id;
	   
	   @Column(name = "name", nullable = false)
	   private String name;
	   
	   @Column(name = "email", nullable = false)
	   private String email;
	   
	   @Column(name = "password", nullable = false)
	   private String password;
	   
	   @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL) 
	   private List<Event> organizedEvents = new ArrayList<>(); 	   // TODO: Attending events
	   
	   @ManyToMany 
	   @JoinTable( 
	           name = "user_attending_events", 
	           joinColumns = @JoinColumn(name = "user_id"), 
	           inverseJoinColumns = @JoinColumn(name = "event_id") 
	   ) 
	   private List<Event> attendingEvents = new ArrayList<>(); 
	   
	   @ManyToMany 
	   @JoinTable( 
			   name = "user_staffing_events", 
			   joinColumns = @JoinColumn(name = "user_id"), 
			   inverseJoinColumns = @JoinColumn(name = "event_id") 
			   ) 
	   private List<Event> staffingEvents = new ArrayList<>(); 
	   
	   @CreatedDate
	   @Column(name = "created_at", updatable = false, nullable = false)
	   private LocalDateTime createdAt;
	   
	   @LastModifiedDate
	   @Column(name = "updated_at", nullable = false)
	   private LocalDateTime updatedAt;

	   @Override
	   public int hashCode() {
		return Objects.hash(createdAt, email, id, name, password, updatedAt);
	   }

	   @Override
	   public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(updatedAt, other.updatedAt);
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

	   public String getEmail() {
		   return email;
	   }

	   public void setEmail(String email) {
		   this.email = email;
	   }

	   public String getPassword() {
		   return password;
	   }

	   public void setPassword(String password) {
		   this.password = password;
	   }

	   public List<Event> getOrganizedEvents() {
		   return organizedEvents;
	   }

	   public void setOrganizedEvents(List<Event> organizedEvents) {
		   this.organizedEvents = organizedEvents;
	   }

	   public List<Event> getAttendingEvents() {
		   return attendingEvents;
	   }

	   public void setAttendingEvents(List<Event> attendingEvents) {
		   this.attendingEvents = attendingEvents;
	   }

	   public List<Event> getStaffingEvents() {
		   return staffingEvents;
	   }

	   public void setStaffingEvents(List<Event> staffingEvents) {
		   this.staffingEvents = staffingEvents;
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
