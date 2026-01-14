package com.ey.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ey.enums.QrCodeStatusEnum;

public class CreateQrCodeResponse {

	private UUID id;
    private QrCodeStatusEnum status;
    private String value;            // e.g., Base64 image string or QR payload
    private UUID ticketId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public QrCodeStatusEnum getStatus() {
		return status;
	}
	public void setStatus(QrCodeStatusEnum status) {
		this.status = status;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public UUID getTicketId() {
		return ticketId;
	}
	public void setTicketId(UUID ticketId) {
		this.ticketId = ticketId;
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
