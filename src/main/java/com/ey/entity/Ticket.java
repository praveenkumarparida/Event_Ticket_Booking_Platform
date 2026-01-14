package com.ey.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.ey.enums.TicketStatusEnum;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

	   @Id
	   @Column(name = "id", nullable = false, updatable = false)
	   @GeneratedValue(strategy = GenerationType.UUID)
	   private UUID id;
	   
	   @Column(name = "status", nullable = false)
	   @Enumerated(EnumType.STRING)
	   private TicketStatusEnum status;
	   
	   @ManyToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name = "ticket_type_id")
	   private TicketType ticketType;
	   
	   @ManyToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name = "purchaser_id")
	   private User purchaser;
	   
	   @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL) 
	   private List<TicketValidation> validations = new ArrayList<>(); 

	   @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL) 
	   private List<QrCode> qrCodes = new ArrayList<>(); 
	   
	   @CreatedDate
	   @Column(name = "created_at", updatable = false, nullable = false)
	   private LocalDateTime createdAt;
	   
	   @LastModifiedDate
	   @Column(name = "updated_at", nullable = false)
	   private LocalDateTime updatedAt;

	   @Override
	   public int hashCode() {
		return Objects.hash(createdAt, id, status, updatedAt);
	   }

	   @Override
	   public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id) && status == other.status
				&& Objects.equals(updatedAt, other.updatedAt);
	   }

	   public UUID getId() {
		   return id;
	   }

	   public void setId(UUID id) {
		   this.id = id;
	   }

	   public TicketStatusEnum getStatus() {
		   return status;
	   }

	   public void setStatus(TicketStatusEnum status) {
		   this.status = status;
	   }

	   public TicketType getTicketType() {
		   return ticketType;
	   }

	   public void setTicketType(TicketType ticketType) {
		   this.ticketType = ticketType;
	   }

	   public User getPurchaser() {
		   return purchaser;
	   }

	   public void setPurchaser(User purchaser) {
		   this.purchaser = purchaser;
	   }

	   public List<TicketValidation> getValidations() {
		   return validations;
	   }

	   public void setValidations(List<TicketValidation> validations) {
		   this.validations = validations;
	   }

	   public List<QrCode> getQrCodes() {
		   return qrCodes;
	   }

	   public void setQrCodes(List<QrCode> qrCodes) {
		   this.qrCodes = qrCodes;
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
